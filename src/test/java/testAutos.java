import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAutoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstacionamientoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGarageImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioAutoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamientoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarageImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistroImpl;

public class testAutos extends SpringTest{

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
	public void queSePuedaRegistrarUnAutoACliente() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente usuario1 = new Cliente();
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setLocalidad("Laferrere");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		
		reg.agregarCliente(usuario1);
		
		Auto auto = new Auto();
		auto.setPatente("aaa123");
		auto.setCliente(usuario1);
		
		Cliente buscado=repo.buscarCliente(usuario1);
		//si Buscado es igual a el Cliente que tiene auto
		assertEquals(buscado, auto.getCliente());
		
	
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaAsignarMasDeUnAutoAUnCliente() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		RepositorioAutoImpl repoA = new RepositorioAutoImpl(sessionFactory);
		ServicioAutoImpl servAuto = new ServicioAutoImpl(repoA);
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("pepe");
		cliente1.setApellido("rodriguez");
		cliente1.setLocalidad("Laferrere");
		cliente1.setEmail("pepito@hotmail.com");
		cliente1.setPassword("123");
		
		
		
		Auto auto = new Auto();
		Auto auto2= new Auto();
		auto.setPatente("asd123");
		auto2.setPatente("aaa555");
		servAuto.registrarAuto(auto);
		servAuto.registrarAuto(auto2);
		
		auto.setCliente(cliente1);
		auto2.setCliente(cliente1);
		reg.agregarCliente(cliente1);
		
		
		assertEquals(2,servAuto.consultarAutoDeCliente(cliente1).size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedeEliminarUnAutoAUnCliente() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		RepositorioAutoImpl repoA = new RepositorioAutoImpl(sessionFactory);
		ServicioAutoImpl servAuto = new ServicioAutoImpl(repoA);
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("pepe");
		cliente1.setApellido("rodriguez");
		cliente1.setLocalidad("Laferrere");
		cliente1.setEmail("pepito@hotmail.com");
		cliente1.setPassword("123");
		
		
		
		Auto auto = new Auto();
		Auto auto2= new Auto();
		auto.setPatente("asd123");
		auto2.setPatente("ART123");
		auto.setEnUso(true);
		auto.setEnUso(true);
		servAuto.registrarAuto(auto);
		servAuto.registrarAuto(auto2);
		
		auto.setCliente(cliente1);
		auto2.setCliente(cliente1);
		
		reg.agregarCliente(cliente1);
		
		repoA.eliminarAuto(auto2);
		
		assertEquals(1,servAuto.consultarAutoDeClienteActivo(cliente1).size());
		
	}
	
	@Test
	@Transactional
	@Rollback
public void queUnAutoIngresaAUnGarage() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		RepositorioAutoImpl repoA = new RepositorioAutoImpl(sessionFactory);
		ServicioAutoImpl servAuto = new ServicioAutoImpl(repoA);
		RepositorioEstacionamientoImpl repoEst= new RepositorioEstacionamientoImpl(sessionFactory);
		ServicioEstacionamientoImpl servEst = new ServicioEstacionamientoImpl(repoEst);
		RepositorioGarageImpl repoG = new RepositorioGarageImpl(sessionFactory);
		ServicioGarageImpl servG = new ServicioGarageImpl(repoG);
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("pepe");
		cliente1.setApellido("rodriguez");
		cliente1.setLocalidad("Localidad");
		cliente1.setEmail("pepito@hotmail.com");
		cliente1.setPassword("123");
		reg.agregarCliente(cliente1);
		
		Auto auto = new Auto();
		auto.setPatente("asd123");
		
		auto.setReservado(true);
		servAuto.registrarAuto(auto);
		//Auto a Cliente
		auto.setCliente(cliente1);
		
		Garage garage = new Garage();
		garage.setCapacidad(10);
		garage.setLocalidad("Laferrere");
		garage.setNombre("Las Palmas");
		servG.agregarGarage(garage);
		

		Estacionamiento est = new Estacionamiento();
		
		servEst.registrarEstacionamiento(est);
		est.setAuto(auto);
		est.setGarage1(garage);
		est.setActiva(true);
		est.setReservado(true);
		servAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
		
		
		
		Integer vp = servEst.buscarAutosQueEstenActivosEnUnGarage(garage).size();
		
		Integer ve = 1;
		assertEquals(ve,vp);
	}
	@Test
	@Transactional
	@Rollback
public void queMasDeUnAutoIngresaAUnGarage() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		RepositorioAutoImpl repoA = new RepositorioAutoImpl(sessionFactory);
		ServicioAutoImpl servAuto = new ServicioAutoImpl(repoA);
		RepositorioEstacionamientoImpl repoEst= new RepositorioEstacionamientoImpl(sessionFactory);
		ServicioEstacionamientoImpl servEst = new ServicioEstacionamientoImpl(repoEst);
		RepositorioGarageImpl repoG = new RepositorioGarageImpl(sessionFactory);
		ServicioGarageImpl servG = new ServicioGarageImpl(repoG);
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("pepe");
		cliente1.setApellido("rodriguez");
		cliente1.setLocalidad("Laferrere");
		cliente1.setEmail("pepito@hotmail.com");
		cliente1.setPassword("123");
		reg.agregarCliente(cliente1);
		
		Auto auto = new Auto();
		auto.setPatente("asd123");
		servAuto.registrarAuto(auto);
		
		Auto auto1 = new Auto();
		auto1.setPatente("asd133");
		servAuto.registrarAuto(auto1);
		
		//Auto a Cliente
		auto1.setCliente(cliente1);
		auto.setCliente(cliente1);
		
		Garage garage = new Garage();
		garage.setCapacidad(10);
		garage.setLocalidad("Laferrere");
		garage.setNombre("Las Palmas");
		servG.agregarGarage(garage);
		

		Estacionamiento est = new Estacionamiento();
		
		servEst.registrarEstacionamiento(est);
		est.setAuto(auto);
		est.setGarage1(garage);
		est.setActiva(true);
		est.setReservado(true);
		servAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
		
		Estacionamiento est1 = new Estacionamiento();
		servEst.registrarEstacionamiento(est1);
		est1.setAuto(auto1);
		est1.setGarage1(garage);
		est1.setActiva(true);
		est1.setReservado(true);
		servAuto.cambiarEstadoDeSiestaEnGarageOno(auto1);
		
		Integer vp = servEst.buscarAutosQueEstenActivosEnUnGarage(garage).size();
		
		Integer ve = 2;
		assertEquals(ve,vp);
		
	}
	@Test
	@Transactional
	@Rollback
public void queSacoUnAutoDeUnGarage() {
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		RepositorioAutoImpl repoA = new RepositorioAutoImpl(sessionFactory);
		ServicioAutoImpl servAuto = new ServicioAutoImpl(repoA);
		RepositorioEstacionamientoImpl repoEst= new RepositorioEstacionamientoImpl(sessionFactory);
		ServicioEstacionamientoImpl servEst = new ServicioEstacionamientoImpl(repoEst);
		RepositorioGarageImpl repoG = new RepositorioGarageImpl(sessionFactory);
		ServicioGarageImpl servG = new ServicioGarageImpl(repoG);
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("pepe");
		cliente1.setApellido("rodriguez");
		cliente1.setLocalidad("Localidad");
		cliente1.setEmail("pepito@hotmail.com");
		cliente1.setPassword("123");
		reg.agregarCliente(cliente1);
		
		Auto auto = new Auto();
		auto.setPatente("asd123");
		servAuto.registrarAuto(auto);
		
		Auto auto1 = new Auto();
		auto1.setPatente("asd133");
		servAuto.registrarAuto(auto1);
		
		//Auto a Cliente
		auto1.setCliente(cliente1);
		auto.setCliente(cliente1);
		
		Garage garage = new Garage();
		garage.setCapacidad(10);
		garage.setLocalidad("Laferrere");
		garage.setNombre("Las Palmas");
		servG.agregarGarage(garage);
		

		Estacionamiento est = new Estacionamiento();
		
		servEst.registrarEstacionamiento(est);
		est.setAuto(auto);
		est.setGarage1(garage);
		est.setActiva(true);
		est.setReservado(true);
		
		servAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
		
		Estacionamiento est1 = new Estacionamiento();
		servEst.registrarEstacionamiento(est1);
		est1.setAuto(auto1);
		est1.setGarage1(garage);
		est1.setActiva(true);
		est1.setReservado(true);
		
		servAuto.cambiarEstadoDeSiestaEnGarageOno(auto1);
		
		// Cambia el Estado de Estacionamiento de Activo a descativo
		// para verificar si esta usando o algun estacionamiento
		servEst.cambiarEstadoEstacionamiento(est1);
		
		
		Integer vp = servEst.buscarAutosQueEstenActivosEnUnGarage(garage).size();
		
		Integer ve = 1;
		assertEquals(ve,vp);
		
	}
	@Test
	@Transactional
	@Rollback
public void queFuncioneElContador() {
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		RepositorioAutoImpl repoA = new RepositorioAutoImpl(sessionFactory);
		ServicioAutoImpl servAuto = new ServicioAutoImpl(repoA);
		RepositorioEstacionamientoImpl repoEst= new RepositorioEstacionamientoImpl(sessionFactory);
		ServicioEstacionamientoImpl servEst = new ServicioEstacionamientoImpl(repoEst);
		RepositorioGarageImpl repoG = new RepositorioGarageImpl(sessionFactory);
		ServicioGarageImpl servG = new ServicioGarageImpl(repoG);
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("pepe");
		cliente1.setApellido("rodriguez");
		cliente1.setLocalidad("Laferrere");
		cliente1.setEmail("pepito@hotmail.com");
		cliente1.setPassword("123");
		reg.agregarCliente(cliente1);
		
		Auto auto = new Auto();
		auto.setPatente("asd123");
		servAuto.registrarAuto(auto);
		
		Auto auto1 = new Auto();
		auto1.setPatente("asd133");
		servAuto.registrarAuto(auto1);
		
		//Auto a Cliente
		auto1.setCliente(cliente1);
		auto.setCliente(cliente1);
		
		Garage garage = new Garage();
		garage.setCapacidad(1);
		garage.setLocalidad("Laferrere");
		garage.setNombre("Las Palmas");
		servG.agregarGarage(garage);
		

		Estacionamiento est = new Estacionamiento();
		
		servEst.registrarEstacionamiento(est);
		est.setAuto(auto);
		est.setGarage1(garage);
		est.setActiva(true);
		est.setReservado(true);
		servAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
		servG.sumarContador(garage);
		
		
		Estacionamiento est1 = new Estacionamiento();
		servEst.registrarEstacionamiento(est1);
		est1.setAuto(auto1);
		est1.setGarage1(garage);
		est1.setActiva(true);
		est1.setReservado(true);
		servG.sumarContador(garage);
		servAuto.cambiarEstadoDeSiestaEnGarageOno(auto1);
		
		Integer ve=2;
		Integer vo=garage.getContador();
		assertEquals(ve,vo);
		
	}
	
	
	@Test
	@Transactional
	@Rollback
	public void queNoPuedaIngresarAutoSiEstaGarageLLeno() {
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		RepositorioAutoImpl repoA = new RepositorioAutoImpl(sessionFactory);
		ServicioAutoImpl servAuto = new ServicioAutoImpl(repoA);
		RepositorioEstacionamientoImpl repoEst= new RepositorioEstacionamientoImpl(sessionFactory);
		ServicioEstacionamientoImpl servEst = new ServicioEstacionamientoImpl(repoEst);
		RepositorioGarageImpl repoG = new RepositorioGarageImpl(sessionFactory);
		ServicioGarageImpl servG = new ServicioGarageImpl(repoG);
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("pepe");
		cliente1.setApellido("rodriguez");
		cliente1.setLocalidad("Laferrere");
		cliente1.setEmail("pepito@hotmail.com");
		cliente1.setPassword("123");
		reg.agregarCliente(cliente1);
		
		Auto auto = new Auto();
		auto.setPatente("asd123");
		servAuto.registrarAuto(auto);
		
		Auto auto1 = new Auto();
		auto1.setPatente("asd133");
		servAuto.registrarAuto(auto1);
		
		//Auto a Cliente
		auto1.setCliente(cliente1);
		auto.setCliente(cliente1);
		
		Garage garage = new Garage();
		garage.setCapacidad(1);
		garage.setLocalidad("Laferrere");
		garage.setNombre("Las Palmas");
		servG.agregarGarage(garage);
		

		Estacionamiento est = new Estacionamiento();
		
		servEst.registrarEstacionamiento(est);
		est.setAuto(auto);
		est.setGarage1(garage);
		est.setActiva(true);
		servAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
		servG.sumarContador(garage);
		Estacionamiento est1 = new Estacionamiento();
		servEst.registrarEstacionamiento(est1);
		est1.setAuto(auto1);
		est1.setGarage1(garage);
		est1.setActiva(true);
		servAuto.cambiarEstadoDeSiestaEnGarageOno(auto1);
		servG.sumarContador(garage);
		
		assertTrue(servG.GarageLleno(garage));
			
	}
    
}
