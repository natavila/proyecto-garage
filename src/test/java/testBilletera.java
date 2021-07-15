import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioBilleteraImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilleteraImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioClienteImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistroImpl;

public class testBilletera extends SpringTest{

	@Inject
	private SessionFactory sessionFactory;
	

	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaAsignarBilleteraACliente() {
		
		RepositorioBilleteraImpl repoBilletera = new RepositorioBilleteraImpl(sessionFactory);
		RepositorioClienteImpl repoCliente = new RepositorioClienteImpl(sessionFactory);
		ServicioBilleteraImpl servicioBilletera = new ServicioBilleteraImpl(repoBilletera);
		ServicioRegistroImpl servicioRegistro = new ServicioRegistroImpl(repoCliente);
		
		Cliente cliente = new Cliente();
		Cliente cliente2 = new Cliente();
		Billetera billetera = new Billetera();
		
		cliente.setNombre("Pepe");
		cliente.setApellido("Santoro");
		cliente.setEmail("pepe@hotmail.com");
		cliente.setLocalidad("Laferrere");
		servicioRegistro.agregarCliente(cliente);
		
		cliente2.setNombre("Pepe2");
		cliente2.setApellido("Santoro");
		cliente2.setEmail("pepe2@hotmail.com");
		cliente2.setLocalidad("Laferrere");
		servicioRegistro.agregarCliente(cliente2);
		
		billetera.setCliente(cliente);
		servicioBilletera.registrarBilletera(billetera);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void quepuedaConsultarSaldoDeCliente() {
		RepositorioBilleteraImpl repoBilletera = new RepositorioBilleteraImpl(sessionFactory);
		RepositorioClienteImpl repoCliente = new RepositorioClienteImpl(sessionFactory);
		ServicioBilleteraImpl servicioBilletera = new ServicioBilleteraImpl(repoBilletera);
		ServicioRegistroImpl servicioRegistro = new ServicioRegistroImpl(repoCliente);
		
		Billetera billetera =  new Billetera();
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Pepe");
		cliente.setApellido("roberto");
		cliente.setLocalidad("Laferrere");
		cliente.setEmail("pepe@hotmail.com");
		
		servicioRegistro.agregarCliente(cliente);
		
		billetera.setCliente(cliente);
		
		servicioBilletera.registrarBilletera(billetera);
		
		billetera.setSaldo(500.0);
		
		Double vo=servicioBilletera.consultarSaldo(billetera);
		Double ve=500.0;
		assertEquals(ve,vo);
	}
	@Test
	@Transactional
	@Rollback
	public void quepuedaIngresarSaldoDeCliente() {
		RepositorioBilleteraImpl repoBilletera = new RepositorioBilleteraImpl(sessionFactory);
		RepositorioClienteImpl repoCliente = new RepositorioClienteImpl(sessionFactory);
		ServicioBilleteraImpl servicioBilletera = new ServicioBilleteraImpl(repoBilletera);
		ServicioRegistroImpl servicioRegistro = new ServicioRegistroImpl(repoCliente);
		
		Billetera billetera =  new Billetera();
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Pepe");
		cliente.setApellido("roberto");
		cliente.setLocalidad("Laferrere");
		cliente.setEmail("pepe@hotmail.com");
		
		servicioRegistro.agregarCliente(cliente);
		
		billetera.setCliente(cliente);
		
		servicioBilletera.registrarBilletera(billetera);
		
		billetera.setSaldo(0.0);
		
		servicioBilletera.ingresarSaldo(billetera, 500.0);
		
		Double ve =500.0;
		Double vo=servicioBilletera.consultarSaldo(billetera);
		assertEquals(ve,vo);
		
	}
	@Test
	@Transactional
	@Rollback
	public void quepuedasumarSaldoACliente() {
	
		RepositorioBilleteraImpl repoBilletera = new RepositorioBilleteraImpl(sessionFactory);
		RepositorioClienteImpl repoCliente = new RepositorioClienteImpl(sessionFactory);
		ServicioBilleteraImpl servicioBilletera = new ServicioBilleteraImpl(repoBilletera);
		ServicioRegistroImpl servicioRegistro = new ServicioRegistroImpl(repoCliente);
		
		Billetera billetera =  new Billetera();
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Pepe");
		cliente.setApellido("roberto");
		cliente.setLocalidad("Laferrere");
		cliente.setEmail("pepe@hotmail.com");
		
		servicioRegistro.agregarCliente(cliente);
		
		billetera.setCliente(cliente);
		
		servicioBilletera.registrarBilletera(billetera);
		
		billetera.setSaldo(0.0);
		
		servicioBilletera.ingresarSaldo(billetera, 500.0);
		servicioBilletera.ingresarSaldo(billetera, 200.0);
		Double ve =700.0;
		Double vo=servicioBilletera.consultarSaldo(billetera);
		assertEquals(ve,vo);
		
		
	}
	
	
	
	
	
	
}
