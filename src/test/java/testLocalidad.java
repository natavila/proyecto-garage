import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocalidadImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioLocalidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioLocalidadImpl;

public class testLocalidad extends SpringTest{
	@Inject
	private SessionFactory sessionFactory;
	
	@Test
    @Transactional 
    @Rollback
    public void queSePuedaRegistrarUnaLocalidad() {
		RepositorioLocalidadImpl repLoc= new RepositorioLocalidadImpl(sessionFactory);
		ServicioLocalidadImpl servLoc= new ServicioLocalidadImpl(repLoc);
		Localidad loc = new Localidad();
		loc.setLocalidad("moron");
		servLoc.agregarLocalidad(loc);
		assertEquals(1, servLoc.consultarLocalidad().size());
	
		}

	@Test
    @Transactional 
    @Rollback
    public void queSePuedaEliminarUnaLocalidad() {
		RepositorioLocalidadImpl repLoc= new RepositorioLocalidadImpl(sessionFactory);
		ServicioLocalidadImpl servLoc= new ServicioLocalidadImpl(repLoc);
		Localidad loc = new Localidad();
		loc.setLocalidad("moron");
		servLoc.agregarLocalidad(loc);
		Long id= loc.getId();
		servLoc.eliminarLocalidad(id);
		assertEquals(0, servLoc.consultarLocalidad().size());
		}
	
	@Test
    @Transactional 
    @Rollback
    public void queSePuedaVerificarSiExisteLocalidad() {
		RepositorioLocalidadImpl repLoc= new RepositorioLocalidadImpl(sessionFactory);
		ServicioLocalidadImpl servLoc= new ServicioLocalidadImpl(repLoc);
		Localidad loc = new Localidad();
		loc.setLocalidad("moron");
		servLoc.agregarLocalidad(loc);
		//SI devuelve un true es q esta La localidad Ya ingresada
		assertTrue(servLoc.verificarSiExisteLocalidad(loc));
		
		
		}
	
	
	
}
