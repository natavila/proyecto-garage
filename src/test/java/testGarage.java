import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGarageImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarageImpl;

public class testGarage extends SpringTest{
	@Inject
	private SessionFactory sessionFactory;
	
	@Test
    @Transactional 
    @Rollback
    public void queSePuedaRegistrarUnGarage() {
		RepositorioGarageImpl garageImp = new RepositorioGarageImpl(sessionFactory);
		ServicioGarageImpl servGarage= new ServicioGarageImpl(garageImp);
		Garage garage1 = new Garage();
		garage1.setCalle("Luro");
		garage1.setCapacidad(20);
		garage1.setNombre("Las Palmas");
		
		servGarage.agregarGarage(garage1);
		
		ArrayList<Garage> g1 = (ArrayList<Garage>) servGarage.consultarGarage();
		
		Integer ve=1;
		Integer vo=g1.size();
		assertEquals(ve,vo);
	
	}
	@Test
    @Transactional 
    @Rollback
    public void queSePuedaEliminarUnGarage() {
		RepositorioGarageImpl garageImp = new RepositorioGarageImpl(sessionFactory);
		ServicioGarageImpl servGarage= new ServicioGarageImpl(garageImp);
		Garage garage1 = new Garage();
		garage1.setCalle("Luro");
		garage1.setCapacidad(20);
		garage1.setNombre("Las Palmas");
		
		servGarage.agregarGarage(garage1);
		//Busco el Id de Garage y  Lo elimino
		
		servGarage.eliminarGarage(garage1.getId());
		ArrayList<Garage> g1 = (ArrayList<Garage>) servGarage.consultarGarage();
		
		Integer ve=0;
		Integer vo=g1.size();
		assertEquals(ve,vo);
	}
	
	
	
}
