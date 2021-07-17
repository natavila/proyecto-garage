package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistroImpl;

@Repository("repositorioGarage")
public class RepositorioGarageImpl implements RepositorioGarage{

	private SessionFactory sessionFactory;
	
	 @Autowired
		public RepositorioGarageImpl(SessionFactory sessionFactory){
			this.sessionFactory = sessionFactory;
		}

	 @Override 
	  public List<Garage> consultarGarage() {
		
		  final Session session = sessionFactory.getCurrentSession();
		  
		   List<Garage> listaGarage = session.createCriteria(Garage.class)
				  .list();
				return listaGarage;  
	  }
	
	
	

	
	
		

	@Override 
	public Boolean agregarGarage(Garage garage1) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		sessionFactory.getCurrentSession().save(garage1);
		return true;
	}

	@Override
	public Garage EliminarGarage(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Garage garage1 = (Garage) session.createCriteria(Garage.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
		
		return garage1;
	}


	
	
	@Override
	public Garage buscarGarage(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return  (Garage) session.createCriteria(Garage.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}

	@Override
	public Garage  contultarUnGarage(Garage garage1) {
		final Session session = sessionFactory.getCurrentSession();
		return (Garage) session.createCriteria(Garage.class)
				.add(Restrictions.eq("id",garage1.getId()))
				.uniqueResult();
	}

	
	@Override
	public List<Auto> consultarAutosEnGarage(Garage garage1){
		final Session session = sessionFactory.getCurrentSession();
		List<Auto> lista= session.createCriteria(Auto.class)
							.createAlias("garage", "garageBuscado")
							.add(Restrictions.eq("garageBuscado.id", garage1.getId()))
							.list();
		return lista;
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Garage> buscarPorLocalidad(Garage garage1) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Garage.class)
				.add(Restrictions.eq("localidad", garage1.getLocalidad()))
				.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Garage> buscarPorPrecioHora(Double precio1, Double precio2) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Garage.class)
		.add(Restrictions.between("precioHora", precio1, precio2))
		.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Garage> buscarPorPrecioMes(Double precio1, Double precio2) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Garage.class)
		.add(Restrictions.between("precioMes", precio1, precio2))
		.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Garage> buscarPorPrecioEstadia(Double precio1, Double precio2) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Garage.class)
		.add(Restrictions.between("precioEstadia", precio1, precio2))
		.list();


	}


	@Override
	public Auto BuscarAutoEnGarage(Auto auto, Garage garage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Garage> buscarGarageQueCoincidanConLocalidadDeCliente(Cliente cliente) {
		final Session session = sessionFactory.getCurrentSession();
		List<Garage> lista =  session.createCriteria(Garage.class)
				.add(Restrictions.eq("localidad", cliente.getLocalidad()))
				.add(Restrictions.eq("activo", true))
				.list();
		return lista;
	}
	@Override
	public void modificarDatosGarage(Garage garage, Garage modificado) {
		final Session session = sessionFactory.getCurrentSession();
		String nombre = garage.getNombre();
		String calle = garage.getCalle();
		Integer capacidad = garage.getCapacidad();
		Integer numero = garage.getNumero();
		Double precioHora = garage.getPrecioHora();
		Double precioEstadia = garage.getPrecioEstadia();
		String localidad = garage.getLocalidad();
		modificado.setNombre(nombre);
		modificado.setCalle(calle);
		modificado.setCapacidad(capacidad);
		modificado.setNumero(numero);
		modificado.setPrecioHora(precioHora);
		modificado.setPrecioEstadia(precioEstadia);
		modificado.setLocalidad(localidad);
		
		session.update(modificado);
	}
	
	

	
	
	
	
	 
	 
}
