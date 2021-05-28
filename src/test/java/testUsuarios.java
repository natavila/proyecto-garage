<<<<<<< HEAD
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
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistroImpl;


public class testUsuarios extends SpringTest{
	
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
		
		usuario2.setNombre("jorge");
		usuario2.setApellido("asd");
		usuario2.setEmail("jorge@hotmail.com");
		usuario2.setPassword("321");
		
		serv.agregarCliente(usuario1);
		serv.agregarCliente(usuario2);
		
		List<Cliente> clientesBD =  (List<Cliente>) session().getSession().createCriteria(Cliente.class)
									.list();
		
		assertEquals(clientesBD.size(), 2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaConcultarUnClienteALaBD() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente usuario1 = new Cliente();
		Cliente usuario2 = new Cliente();
		
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setDni(1234564);
		
		usuario2.setNombre("jorge");
		usuario2.setApellido("asd");
		usuario2.setEmail("jorge@hotmail.com");
		usuario2.setPassword("321");
		usuario2.setDni(42671687);
		
		reg.agregarCliente(usuario1);
		reg.agregarCliente(usuario2);
		
		System.out.println(log.listaDeClientes());
		
		List<Cliente> clienteEncontrado =  (List<Cliente>) sessionFactory.getCurrentSession()
				.createCriteria(Cliente.class)
				.add(Restrictions.eq("dni", usuario1.getDni()))
				.list();
		
		assertEquals(1, clienteEncontrado.size());
		
		
		
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queElClientePuedaPagarElAlquiler() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente usuario1 = new Cliente();
		Auto auto1 = new Auto();
		Garage garage1 = new Garage();
		Localidad localidad = new Localidad();
		
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setDni(1234564);
		
		garage1.setNombre("nose");
		
		
		auto1.setPatente("asd123");
		auto1.setCliente(usuario1);
		
		reg.agregarCliente(usuario1);
		
		
		
	}
	

}
=======
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
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistroImpl;


public class testUsuarios extends SpringTest{
	
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
		
		usuario2.setNombre("jorge");
		usuario2.setApellido("asd");
		usuario2.setEmail("jorge@hotmail.com");
		usuario2.setPassword("321");
		
		serv.agregarCliente(usuario1);
		serv.agregarCliente(usuario2);
		
		List<Cliente> clientesBD =  (List<Cliente>) session().getSession().createCriteria(Cliente.class)
									.list();
		
		assertEquals(clientesBD.size(), 2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaConcultarUnClienteALaBD() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente usuario1 = new Cliente();
		Cliente usuario2 = new Cliente();
		
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setDni(1234564);
		
		usuario2.setNombre("jorge");
		usuario2.setApellido("asd");
		usuario2.setEmail("jorge@hotmail.com");
		usuario2.setPassword("321");
		usuario2.setDni(42671687);
		
		reg.agregarCliente(usuario1);
		reg.agregarCliente(usuario2);
		
		System.out.println(log.listaDeClientes());
		
		List<Cliente> clienteEncontrado =  (List<Cliente>) sessionFactory.getCurrentSession()
				.createCriteria(Cliente.class)
				.add(Restrictions.eq("dni", usuario1.getDni()))
				.list();
		
		assertEquals(1, clienteEncontrado.size());
		
		
		
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queElClientePuedaPagarElAlquiler() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente usuario1 = new Cliente();
		Auto auto1 = new Auto();
		Garage garage1 = new Garage();
		Localidad localidad = new Localidad();
		
		usuario1.setNombre("pepe");
		usuario1.setApellido("rodriguez");
		usuario1.setEmail("pepito@hotmail.com");
		usuario1.setPassword("123");
		usuario1.setDni(1234564);
		
		garage1.setNombre("nose");
		
		
		auto1.setPatente("asd123");
		auto1.setCliente(usuario1);
		
		reg.agregarCliente(usuario1);
		
		
		
	}
	

}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
