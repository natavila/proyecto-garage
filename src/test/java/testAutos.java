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
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAutoImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioAutoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
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
		servAuto.eliminarAuto(auto);
		
		assertEquals(1,servAuto.consultarAutoDeCliente(cliente1).size());
		
	}
	
	@Test
	@Transactional
	@Rollback
public void queUnAutoIngresaAUnGarage() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		RepositorioAutoImpl repoA = new RepositorioAutoImpl(sessionFactory);
		ServicioAutoImpl servAuto = new ServicioAutoImpl(repoA);
		
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("pepe");
		cliente1.setApellido("rodriguez");
		cliente1.setEmail("pepito@hotmail.com");
		cliente1.setPassword("123");
		reg.agregarCliente(cliente1);
		
		Auto auto = new Auto();
		auto.setPatente("asd123");
		servAuto.registrarAuto(auto);
		
		//Auto a Cliente
		auto.setCliente(cliente1);
		
		
		
	}
	
    
}
