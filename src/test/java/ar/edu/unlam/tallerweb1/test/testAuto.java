package ar.edu.unlam.tallerweb1.test;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistroImpl;

public class testAuto extends SpringTest {

	@Inject
	private SessionFactory sessionFactory;
	
	@Test
	@Transactional 
	@Rollback
	public void testQuePuedaRegistrarUnAutoACliente() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente usuario1 = new Cliente();
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
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

}
