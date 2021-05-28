<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
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
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
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
	public void queSePuedaRegistrarUnAuto() {
		
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
		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		auto1.setPatente("asd123");
		auto1.setCliente(usuario1);
		auto2.setPatente("wtf321");
		auto2.setCliente(usuario2);
		
		reg.agregarCliente(usuario2);
		reg.registrarAuto(auto1);
		reg.agregarCliente(usuario1);
		reg.registrarAuto(auto2);
		
		List<Auto> autosBD =  (List<Auto>) session().getSession().createCriteria(Auto.class)
				.list();

		assertEquals(autosBD.size(), 2); 
		
		System.out.println(autosBD);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaAsignarUnAutoAUnClienteDeLaBD() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		Cliente cliente3 = new Cliente();
		
		cliente1.setDni(123456);
		cliente2.setDni(654321);
		cliente3.setDni(789456);
		
		reg.agregarCliente(cliente1);
		reg.agregarCliente(cliente2);
		reg.agregarCliente(cliente3);
		
		Auto auto = new Auto();
		
		auto.setPatente("asd123");
		
		reg.registrarAuto(auto);
		
		Integer dniBuscado = 789456;
		
		// Se guardan los clientes en la base de datos en forma de lista
		Cliente clienteEncontrado =  (Cliente) sessionFactory.getCurrentSession()
				.createCriteria(Cliente.class)
				.add(Restrictions.eq("dni", dniBuscado))
				.uniqueResult();
			
			auto.setCliente(clienteEncontrado);
			
			System.out.println(auto);
		
		//Recorre la liste y busca mediant el dni el cliente al cual asignarle el auto
		/*for(Cliente cliente : clientesBD) {
			if(cliente.getDni().equals(dniBuscado)) {
				auto.setCliente(cliente);
				System.out.println(auto);
			}
		}*/
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedeAsignarUnAutoAUnGarage() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		Auto auto3 = new Auto();
		Localidad localidad1 = new Localidad();
		Localidad localidad2 = new Localidad();
		Garage garage1 = new Garage();
		Garage garage2 = new Garage();
		Garage garage3 = new Garage();
		
		auto1.setPatente("123asd");
		auto2.setPatente("qwe456");
		auto3.setPatente("ghj456");
		localidad1.setLocalidad("Moron");
		localidad2.setLocalidad("Merlo");
		garage1.setNombre("asd");
		garage2.setNombre("qwe");
		garage3.setNombre("nose");
		garage1.setLocalidad(localidad2);
		garage2.setLocalidad(localidad1);
		garage3.setLocalidad(localidad1);
		
		reg.registrarLocalidad(localidad2);
		reg.registrarLocalidad(localidad1);
		
		reg.registrarGarage(garage1);
		reg.registrarGarage(garage2);
		reg.registrarGarage(garage3);
		
		reg.registrarAuto(auto1);
		reg.registrarAuto(auto2);
		reg.registrarAuto(auto3);
		
		String garageBuscado = "Merlo";
		String nombreBuscado = "asd";
		
		Garage garageEncontrado = (Garage) sessionFactory.getCurrentSession()
				.createCriteria(Garage.class)
				.createAlias("localidad", "garageBuscado")
				.createAlias("garageBuscado.localidad", "localidadBuscada")
				.createAlias("nombre", "nombreGarage")
				.add(Restrictions.eq("localiadadBuscada", garageBuscado))
				.add(Restrictions.eq("nombreGarage", nombreBuscado))
				.uniqueResult();
		
		auto1.setGarage(garageEncontrado);
		
		System.out.println(auto1);
		
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
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
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
	public void queSePuedaRegistrarUnAuto() {
		
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
		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		auto1.setPatente("asd123");
		auto1.setCliente(usuario1);
		auto2.setPatente("wtf321");
		auto2.setCliente(usuario2);
		
		reg.agregarCliente(usuario2);
		reg.registrarAuto(auto1);
		reg.agregarCliente(usuario1);
		reg.registrarAuto(auto2);
		
		List<Auto> autosBD =  (List<Auto>) session().getSession().createCriteria(Auto.class)
				.list();

		assertEquals(autosBD.size(), 2); 
		
		System.out.println(autosBD);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaAsignarUnAutoAUnClienteDeLaBD() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		Cliente cliente3 = new Cliente();
		
		cliente1.setDni(123456);
		cliente2.setDni(654321);
		cliente3.setDni(789456);
		
		reg.agregarCliente(cliente1);
		reg.agregarCliente(cliente2);
		reg.agregarCliente(cliente3);
		
		Auto auto = new Auto();
		
		auto.setPatente("asd123");
		
		reg.registrarAuto(auto);
		
		Integer dniBuscado = 789456;
		
		// Se guardan los clientes en la base de datos en forma de lista
		Cliente clienteEncontrado =  (Cliente) sessionFactory.getCurrentSession()
				.createCriteria(Cliente.class)
				.add(Restrictions.eq("dni", dniBuscado))
				.uniqueResult();
			
			auto.setCliente(clienteEncontrado);
			
			System.out.println(auto);
		
		//Recorre la liste y busca mediant el dni el cliente al cual asignarle el auto
		/*for(Cliente cliente : clientesBD) {
			if(cliente.getDni().equals(dniBuscado)) {
				auto.setCliente(cliente);
				System.out.println(auto);
			}
		}*/
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedeAsignarUnAutoAUnGarage() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		Auto auto3 = new Auto();
		Localidad localidad1 = new Localidad();
		Localidad localidad2 = new Localidad();
		Garage garage1 = new Garage();
		Garage garage2 = new Garage();
		Garage garage3 = new Garage();
		
		auto1.setPatente("123asd");
		auto2.setPatente("qwe456");
		auto3.setPatente("ghj456");
		localidad1.setLocalidad("Moron");
		localidad2.setLocalidad("Merlo");
		garage1.setNombre("asd");
		garage2.setNombre("qwe");
		garage3.setNombre("nose");
		garage1.setLocalidad(localidad2);
		garage2.setLocalidad(localidad1);
		garage3.setLocalidad(localidad1);
		
		reg.registrarLocalidad(localidad2);
		reg.registrarLocalidad(localidad1);
		
		reg.registrarGarage(garage1);
		reg.registrarGarage(garage2);
		reg.registrarGarage(garage3);
		
		reg.registrarAuto(auto1);
		reg.registrarAuto(auto2);
		reg.registrarAuto(auto3);
		
		String garageBuscado = "Merlo";
		String nombreBuscado = "asd";
		
		Garage garageEncontrado = (Garage) sessionFactory.getCurrentSession()
				.createCriteria(Garage.class)
				.createAlias("localidad", "garageBuscado")
				.createAlias("garageBuscado.localidad", "localidadBuscada")
				.createAlias("nombre", "nombreGarage")
				.add(Restrictions.eq("localiadadBuscada", garageBuscado))
				.add(Restrictions.eq("nombreGarage", nombreBuscado))
				.uniqueResult();
		
		auto1.setGarage(garageEncontrado);
		
		System.out.println(auto1);
		
	}
	
    
}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
=======
>>>>>>> branch 'ramaNataly' of https://github.com/natavila/proyecto-garage.git
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
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
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
	public void queSePuedaRegistrarUnAuto() {
		
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
		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		auto1.setPatente("asd123");
		auto1.setCliente(usuario1);
		auto2.setPatente("wtf321");
		auto2.setCliente(usuario2);
		
		reg.agregarCliente(usuario2);
		reg.registrarAuto(auto1);
		reg.agregarCliente(usuario1);
		reg.registrarAuto(auto2);
		
		List<Auto> autosBD =  (List<Auto>) session().getSession().createCriteria(Auto.class)
				.list();

		assertEquals(autosBD.size(), 2); 
		
		System.out.println(autosBD);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaAsignarUnAutoAUnClienteDeLaBD() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		Cliente cliente3 = new Cliente();
		
		cliente1.setDni(123456);
		cliente2.setDni(654321);
		cliente3.setDni(789456);
		
		reg.agregarCliente(cliente1);
		reg.agregarCliente(cliente2);
		reg.agregarCliente(cliente3);
		
		Auto auto = new Auto();
		
		auto.setPatente("asd123");
		
		reg.registrarAuto(auto);
		
		Integer dniBuscado = 789456;
		
		// Se guardan los clientes en la base de datos en forma de lista
		Cliente clienteEncontrado =  (Cliente) sessionFactory.getCurrentSession()
				.createCriteria(Cliente.class)
				.add(Restrictions.eq("dni", dniBuscado))
				.uniqueResult();
			
			auto.setCliente(clienteEncontrado);
			
			System.out.println(auto);
		
		//Recorre la liste y busca mediant el dni el cliente al cual asignarle el auto
		/*for(Cliente cliente : clientesBD) {
			if(cliente.getDni().equals(dniBuscado)) {
				auto.setCliente(cliente);
				System.out.println(auto);
			}
		}*/
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedeAsignarUnAutoAUnGarage() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		Auto auto3 = new Auto();
		Localidad localidad1 = new Localidad();
		Localidad localidad2 = new Localidad();
		Garage garage1 = new Garage();
		Garage garage2 = new Garage();
		Garage garage3 = new Garage();
		
		auto1.setPatente("123asd");
		auto2.setPatente("qwe456");
		auto3.setPatente("ghj456");
		localidad1.setLocalidad("Moron");
		localidad2.setLocalidad("Merlo");
		garage1.setNombre("asd");
		garage2.setNombre("qwe");
		garage3.setNombre("nose");
		garage1.setLocalidad(localidad2);
		garage2.setLocalidad(localidad1);
		garage3.setLocalidad(localidad1);
		
		reg.registrarLocalidad(localidad2);
		reg.registrarLocalidad(localidad1);
		
		reg.registrarGarage(garage1);
		reg.registrarGarage(garage2);
		reg.registrarGarage(garage3);
		
		reg.registrarAuto(auto1);
		reg.registrarAuto(auto2);
		reg.registrarAuto(auto3);
		
		String garageBuscado = "Merlo";
		String nombreBuscado = "asd";
		
		Garage garageEncontrado = (Garage) sessionFactory.getCurrentSession()
				.createCriteria(Garage.class)
				.createAlias("localidad", "garageBuscado")
				.createAlias("garageBuscado.localidad", "localidadBuscada")
				.createAlias("nombre", "nombreGarage")
				.add(Restrictions.eq("localiadadBuscada", garageBuscado))
				.add(Restrictions.eq("nombreGarage", nombreBuscado))
				.uniqueResult();
		
		auto1.setGarage(garageEncontrado);
		
		System.out.println(auto1);
		
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
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioClienteImpl;
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
	public void queSePuedaRegistrarUnAuto() {
		
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
		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		auto1.setPatente("asd123");
		auto1.setCliente(usuario1);
		auto2.setPatente("wtf321");
		auto2.setCliente(usuario2);
		
		reg.agregarCliente(usuario2);
		reg.registrarAuto(auto1);
		reg.agregarCliente(usuario1);
		reg.registrarAuto(auto2);
		
		List<Auto> autosBD =  (List<Auto>) session().getSession().createCriteria(Auto.class)
				.list();

		assertEquals(autosBD.size(), 2); 
		
		System.out.println(autosBD);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedaAsignarUnAutoAUnClienteDeLaBD() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		Cliente cliente3 = new Cliente();
		
		cliente1.setDni(123456);
		cliente2.setDni(654321);
		cliente3.setDni(789456);
		
		reg.agregarCliente(cliente1);
		reg.agregarCliente(cliente2);
		reg.agregarCliente(cliente3);
		
		Auto auto = new Auto();
		
		auto.setPatente("asd123");
		
		reg.registrarAuto(auto);
		
		Integer dniBuscado = 789456;
		
		// Se guardan los clientes en la base de datos en forma de lista
		Cliente clienteEncontrado =  (Cliente) sessionFactory.getCurrentSession()
				.createCriteria(Cliente.class)
				.add(Restrictions.eq("dni", dniBuscado))
				.uniqueResult();
			
			auto.setCliente(clienteEncontrado);
			
			System.out.println(auto);
		
		//Recorre la liste y busca mediant el dni el cliente al cual asignarle el auto
		/*for(Cliente cliente : clientesBD) {
			if(cliente.getDni().equals(dniBuscado)) {
				auto.setCliente(cliente);
				System.out.println(auto);
			}
		}*/
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void queSePuedeAsignarUnAutoAUnGarage() {
		
		RepositorioClienteImpl repo = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl reg = new ServicioRegistroImpl(repo);
		ServicioLoginImpl log = new ServicioLoginImpl(repo);
		
		Auto auto1 = new Auto();
		Auto auto2 = new Auto();
		Auto auto3 = new Auto();
		Localidad localidad1 = new Localidad();
		Localidad localidad2 = new Localidad();
		Garage garage1 = new Garage();
		Garage garage2 = new Garage();
		Garage garage3 = new Garage();
		
		auto1.setPatente("123asd");
		auto2.setPatente("qwe456");
		auto3.setPatente("ghj456");
		localidad1.setLocalidad("Moron");
		localidad2.setLocalidad("Merlo");
		garage1.setNombre("asd");
		garage2.setNombre("qwe");
		garage3.setNombre("nose");
		garage1.setLocalidad(localidad2);
		garage2.setLocalidad(localidad1);
		garage3.setLocalidad(localidad1);
		
		reg.registrarLocalidad(localidad2);
		reg.registrarLocalidad(localidad1);
		
		reg.registrarGarage(garage1);
		reg.registrarGarage(garage2);
		reg.registrarGarage(garage3);
		
		reg.registrarAuto(auto1);
		reg.registrarAuto(auto2);
		reg.registrarAuto(auto3);
		
		String garageBuscado = "Merlo";
		String nombreBuscado = "asd";
		
		Garage garageEncontrado = (Garage) sessionFactory.getCurrentSession()
				.createCriteria(Garage.class)
				.createAlias("localidad", "garageBuscado")
				.createAlias("garageBuscado.localidad", "localidadBuscada")
				.createAlias("nombre", "nombreGarage")
				.add(Restrictions.eq("localiadadBuscada", garageBuscado))
				.add(Restrictions.eq("nombreGarage", nombreBuscado))
				.uniqueResult();
		
		auto1.setGarage(garageEncontrado);
		
		System.out.println(auto1);
		
	}
	
    
}
>>>>>>> refs/remotes/origin/SantosGaston
<<<<<<< HEAD
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
=======
>>>>>>> branch 'ramaNataly' of https://github.com/natavila/proyecto-garage.git
