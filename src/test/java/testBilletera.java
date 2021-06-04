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
		ServicioClienteImpl servicioCliente = new ServicioClienteImpl(repoCliente);
		ServicioBilleteraImpl servicioBilletera = new ServicioBilleteraImpl(repoBilletera);
		ServicioRegistroImpl servicioRegistro = new ServicioRegistroImpl(repoCliente);
		
		Cliente cliente = new Cliente();
		Cliente cliente2 = new Cliente();
		Billetera billetera = new Billetera();
		
		cliente.setNombre("Pepe");
		cliente.setEmail("pepe@hotmail.com");
		servicioRegistro.agregarCliente(cliente);
		
		cliente2.setNombre("Pepe2");
		cliente2.setEmail("pepe2@hotmail.com");
		servicioRegistro.agregarCliente(cliente2);
		
		billetera.setAlias("elpepe");
		billetera.setCliente(cliente);
		servicioBilletera.registrarBilletera(billetera);
		
		
		
	}
}
