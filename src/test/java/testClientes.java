import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.controladores.ControladorRegistro;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAutoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioBilletera;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioBilleteraImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstacionamiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstacionamientoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGarage;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGarageImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioAutoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilleteraImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioClienteImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobrarTickets;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobrarTicketsImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamientoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarageImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistroImpl;


public class testClientes extends SpringTest{
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Test
    @Transactional 
    @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaRegistrarUnUsuarioALaBD(){
		
		Cliente usuario1 = new Cliente();
		Cliente usuario2 = new Cliente();
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv = new ServicioRegistroImpl(repo);
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setLocalidad("Merlo");
		usuario1.setRoll("cliente");
		
		usuario2.setNombre("jorge");
		usuario2.setApellido("asd");
		usuario2.setEmail("jorge@hotmail.com");
		usuario2.setPassword("321");
		usuario2.setLocalidad("San Justo");
		usuario1.setRoll("cliente");
		
		serv.agregarCliente(usuario1);
		serv.agregarCliente(usuario2);
		
		List<Cliente> clientesBD =  (List<Cliente>) session().getSession().createCriteria(Cliente.class)
									.list();
		
		assertEquals(clientesBD.size(), 2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queElClienteNoPuedaIngresarUnCorreoYaRegistrado() {
		
		Cliente usuario1 = new Cliente();
		Cliente usuario2 = new Cliente();
		
		RepositorioCliente repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistro serv = new ServicioRegistroImpl(repo);
		ServicioCliente servCliente = new ServicioClienteImpl(repo);
		ServicioLogin servLogin = new ServicioLoginImpl(repo);
		
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setLocalidad("Merlo");
		usuario1.setRoll("cliente");
		
		if(servLogin.verificarCorreo(usuario1) == null) {
			serv.agregarCliente(usuario1);
		}
		
		usuario2.setNombre("jorge");
		usuario2.setApellido("asd");
		usuario2.setEmail("pepito@hotmail.com");
		usuario2.setPassword("321");
		usuario2.setLocalidad("San Justo");
		usuario1.setRoll("cliente");
		
		if(servLogin.verificarCorreo(usuario2) == null) {
			serv.agregarCliente(usuario2);
		}
		
		
		List<Cliente> clientesBD =  servCliente.listaCliente();
		
		assertEquals(clientesBD.size(), 1);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaConsultarUnClienteALaBD() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		
		Cliente usuario1 = new Cliente();
		Cliente usuario2 = new Cliente();
		
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setLocalidad("Merlo");
		
		
		usuario2.setNombre("jorge");
		usuario2.setApellido("asd");
		usuario2.setEmail("jorge@hotmail.com");
		usuario2.setPassword("321");
		usuario2.setLocalidad("San Justo");
		
		
		reg.agregarCliente(usuario1);
		reg.agregarCliente(usuario2);
		
		List<Cliente> clienteEncontrado =  (List<Cliente>) sessionFactory.getCurrentSession()
				.createCriteria(Cliente.class)
				.add(Restrictions.eq("id", usuario1.getId()))
				.list();
		
		assertEquals(1, clienteEncontrado.size());	
	}
	
	@Test
	@Transactional
	@Rollback
	public void queClientePuedaIngresarDineroALaBilletera() {
		
		RepositorioCliente repo = new RepositorioClienteImpl(sessionFactory);
		RepositorioBilletera repoBilletera = new RepositorioBilleteraImpl(sessionFactory);
		ServicioRegistro reg = new ServicioRegistroImpl(repo);
		ServicioBilletera servBilletera = new ServicioBilleteraImpl(repoBilletera);
		ServicioCliente servCliente = new ServicioClienteImpl(repo);
		
		Cliente usuario1 = new Cliente();
		Billetera billetera1 = new Billetera();
		
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setLocalidad("Merlo");
		usuario1.setRoll("cliente");
		
		reg.agregarCliente(usuario1);
		
		List<Cliente> clientesBD =  servCliente.listaCliente();

		assertEquals(clientesBD.size(), 1);
		
		billetera1.setCliente(usuario1);
		billetera1.setSaldo(0.0);
		
		servBilletera.registrarBilletera(billetera1);
		
		List<Billetera> billeterasBD = servBilletera.consultarBilleteras();
		
		assertEquals(billeterasBD.size(), 1);
		
		Billetera billeteraDeCliente = servBilletera.consultarBilleteraDeCliente(usuario1);
		
		assertEquals(billetera1, billeteraDeCliente);
		
		servBilletera.ingresarSaldo(billeteraDeCliente, 500.0);
		
		Double valorEsperado = 500.0;
				
		assertEquals(billetera1.getSaldo(), valorEsperado);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queElClientePuedaModificarSusDatos() {
		
		RepositorioCliente repoCliente = new RepositorioClienteImpl(sessionFactory);
		
		ServicioCliente servCliente = new ServicioClienteImpl(repoCliente);
		ServicioRegistro reg = new ServicioRegistroImpl(repoCliente);
		
		Cliente usuario1 = new Cliente();
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setLocalidad("Merlo");
		usuario1.setRoll("cliente");		
		
		reg.agregarCliente(usuario1);
		
		usuario1.setApellido("asd");
		
		servCliente.modificarDatosCliente(usuario1, usuario1);
		
		List<Cliente> clientesBD =  servCliente.listaCliente();

		assertEquals(clientesBD.size(), 1);
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void queElClientePuedaPagarReservaEstadia() {
		
		RepositorioCliente repoCliente = new RepositorioClienteImpl(sessionFactory);
		RepositorioAuto repoAuto = new RepositorioAutoImpl(sessionFactory);
		RepositorioEstacionamiento repoEst = new RepositorioEstacionamientoImpl(sessionFactory);
		RepositorioGarage repoGarage = new RepositorioGarageImpl(sessionFactory);
		RepositorioBilletera repoBilletera = new RepositorioBilleteraImpl(sessionFactory);
		
		ServicioRegistro reg = new ServicioRegistroImpl(repoCliente);
		ServicioCliente servCliente = new ServicioClienteImpl(repoCliente);
		ServicioAuto servAuto = new ServicioAutoImpl(repoAuto);
		ServicioEstacionamiento servEst = new ServicioEstacionamientoImpl(repoEst);
		ServicioCobrarTickets servTicket = new ServicioCobrarTicketsImpl(repoEst);
		ServicioGarage servGarage = new ServicioGarageImpl(repoGarage);
		ServicioBilletera servBilletera = new ServicioBilleteraImpl(repoBilletera);
		
		
		Cliente usuario1 = new Cliente();
		Billetera billetera1 = new Billetera();
		Estacionamiento estacionamiento1 = new Estacionamiento();
		Auto auto1 = new Auto();
		Garage garage1 = new Garage();
		
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setLocalidad("Merlo");
		usuario1.setRoll("cliente");
		
		billetera1.setCliente(usuario1);
		billetera1.setSaldo(2000.0);
		
		garage1.setNombre("nose");
		garage1.setPrecioEstadia(70.0);
		
		auto1.setPatente("asd123");
		auto1.setCliente(usuario1);
		
		reg.agregarCliente(usuario1);
		servGarage.agregarGarage(garage1);
		servBilletera.registrarBilletera(billetera1);
		servAuto.registrarAuto(auto1);
		
		List<Cliente> clientesBD =  servCliente.listaCliente();

		assertEquals(clientesBD.size(), 1);
		
		List<Garage> garagesBD =  servGarage.consultarGarage();

		assertEquals(garagesBD.size(), 1);
		
		Long diasEnGarage = servTicket.calcularDias("2021-07-22", "2021-07-24");
		Long diasEnGarageEsperado = 2L;
		
		assertEquals(diasEnGarage, diasEnGarageEsperado);
		
		Double precioEstimado = servTicket.calcularPrecioPorEstadia(garage1.getPrecioEstadia(), "2021-07-22", "2021-07-24");
		Double precioReal = 140.0;
		
		assertEquals(precioEstimado, precioReal);
		
		estacionamiento1.setCliente(usuario1);
		estacionamiento1.setAuto(auto1);
		estacionamiento1.setEstaPagado(false);
		estacionamiento1.setActiva(false);
		estacionamiento1.setReservado(false);
		estacionamiento1.setGarage1(garage1);
		estacionamiento1.setFechaDesde("2021-07-22");
		estacionamiento1.setFechaHasta("2021-07-24");
		estacionamiento1.setPrecioAPagar(precioReal);
		
		servEst.registrarEstacionamiento(estacionamiento1);
		
		List<Estacionamiento> estacionamientoBD =  servEst.buscarEstacionamientoPorCliente(usuario1);
		
		assertEquals(estacionamientoBD.size(), 1);
		
		Billetera valorObtenido = servBilletera.consultarBilleteraDeCliente(usuario1);
		
		assertEquals(billetera1, valorObtenido);
		
		servBilletera.pagarReservaEstadia(estacionamiento1, billetera1);
		
		servEst.cambiarEstadoEstacionamiento(estacionamiento1);
		servEst.cambiarEstadoDeReserva(estacionamiento1);
		
		assertEquals(estacionamiento1.getEstaPagado(), true);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queElClientePuedaPagarReservaPorHora() {
		
		RepositorioCliente repoCliente = new RepositorioClienteImpl(sessionFactory);
		RepositorioAuto repoAuto = new RepositorioAutoImpl(sessionFactory);
		RepositorioEstacionamiento repoEst = new RepositorioEstacionamientoImpl(sessionFactory);
		RepositorioGarage repoGarage = new RepositorioGarageImpl(sessionFactory);
		RepositorioBilletera repoBilletera = new RepositorioBilleteraImpl(sessionFactory);
		
		ServicioRegistro reg = new ServicioRegistroImpl(repoCliente);
		ServicioCliente servCliente = new ServicioClienteImpl(repoCliente);
		ServicioAuto servAuto = new ServicioAutoImpl(repoAuto);
		ServicioEstacionamiento servEst = new ServicioEstacionamientoImpl(repoEst);
		ServicioCobrarTickets servTicket = new ServicioCobrarTicketsImpl(repoEst);
		ServicioGarage servGarage = new ServicioGarageImpl(repoGarage);
		ServicioBilletera servBilletera = new ServicioBilleteraImpl(repoBilletera);
		
		
		Cliente usuario1 = new Cliente();
		Billetera billetera1 = new Billetera();
		Estacionamiento estacionamiento1 = new Estacionamiento();
		Auto auto1 = new Auto();
		Garage garage1 = new Garage();
		
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setLocalidad("Merlo");
		usuario1.setRoll("cliente");
		
		billetera1.setCliente(usuario1);
		billetera1.setSaldo(2000.0);
		
		garage1.setNombre("nose");
		garage1.setPrecioHora(50.0);
		
		auto1.setPatente("asd123");
		auto1.setCliente(usuario1);
		
		reg.agregarCliente(usuario1);
		servGarage.agregarGarage(garage1);
		servBilletera.registrarBilletera(billetera1);
		servAuto.registrarAuto(auto1);
		
		List<Cliente> clientesBD =  servCliente.listaCliente();

		assertEquals(clientesBD.size(), 1);
		
		List<Garage> garagesBD =  servGarage.consultarGarage();

		assertEquals(garagesBD.size(), 1);
		
		Long horasEnGarage = servTicket.calcularHoras("12:00", "14:00");
		Long HorasEnGarageEsperado = 2L;
		
		assertEquals(horasEnGarage, HorasEnGarageEsperado);
		
		Double precioEstimado = servTicket.calcularPrecioPorHora(garage1.getPrecioHora(), "12:00", "14:00");
		Double precioReal = 100.0;
		
		assertEquals(precioEstimado, precioReal);
		
		estacionamiento1.setCliente(usuario1);
		estacionamiento1.setAuto(auto1);
		estacionamiento1.setEstaPagado(false);
		estacionamiento1.setActiva(false);
		estacionamiento1.setReservado(false);
		estacionamiento1.setGarage1(garage1);
		estacionamiento1.setHoraDesde("12:00");
		estacionamiento1.setHoraHasta("14:00");
		estacionamiento1.setPrecioAPagar(precioReal);
		
		servEst.registrarEstacionamiento(estacionamiento1);
		
		List<Estacionamiento> estacionamientoBD =  servEst.buscarEstacionamientoPorCliente(usuario1);
		
		assertEquals(estacionamientoBD.size(), 1);
		
		Billetera valorObtenido = servBilletera.consultarBilleteraDeCliente(usuario1);
		
		assertEquals(billetera1, valorObtenido);
		
		servBilletera.pagarReservaPorHora(estacionamiento1, billetera1);
		
		servEst.cambiarEstadoEstacionamiento(estacionamiento1);
		servEst.cambiarEstadoDeReserva(estacionamiento1);
		
		assertEquals(estacionamiento1.getEstaPagado(), true);
		
	}
	
	
}
