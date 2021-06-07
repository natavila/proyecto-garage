package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;

@Repository("repositorioEstacionamiento")
public class RepositorioEstacionamientoImpl implements RepositorioEstacionamiento{
	private SessionFactory sessionFactory;
	private ServicioAuto servicioAuto;
	private ServicioGarage servicioGarage;
	
	 @Autowired
		public RepositorioEstacionamientoImpl(SessionFactory sessionFactory){
			this.sessionFactory = sessionFactory;
		}
	 	
	 @Override
	 public void registrarEstacionamiento(Estacionamiento est) {
		 final Session session = sessionFactory.getCurrentSession();
		 session.save(est);
	 }
	 
	 @Override
	
		public Estacionamiento buscarEstacionamiento(Long id) {
			final Session session = sessionFactory.getCurrentSession();
			return session.get(Estacionamiento.class, id);
		}
	 @Override
	 public List<Estacionamiento> buscarAutosDeUnGarage(Garage garage1) {
		 final Session session = sessionFactory.getCurrentSession();
		 List<Estacionamiento> garage =  (List<Estacionamiento>) session.createCriteria(Estacionamiento.class)
				 .createAlias("garage1","garageBuscado")
				 .add(Restrictions.eq("garageBuscado.id",garage1.getId()))
				 .list(); 
		 return garage;		 
	 }
	
	@Override
	public Long calcularDias(String desde, String hasta) {
		
		LocalDate desdePars = LocalDate.parse(desde);
		LocalDate hastaPars = LocalDate.parse(hasta);
		
		Long diasEnGarage = DAYS.between(desdePars, hastaPars);
		
		return diasEnGarage;
	}

	@Override
	public Long calcularHoras(String desde, String hasta) {
		
		LocalTime desdePars = LocalTime.parse(desde);
		LocalTime hastaPars = LocalTime.parse(hasta);
		
		Long horasEnGarage = ChronoUnit.HOURS.between(desdePars, hastaPars);
		
		return horasEnGarage;
	}

	@Override
	public Double calcularPrecioPorEstadia(Double precio, String desde, String hasta) {
		
		LocalDate desdePars = LocalDate.parse(desde);
		LocalDate hastaPars = LocalDate.parse(hasta);
		
		Long diasEnGarage = DAYS.between(desdePars, hastaPars);
		
		Double total = precio * diasEnGarage;
		
		return total;
	}
	
	@Override
	public Double calcularPrecioPorHora(Double precio, String desde, String hasta) {
		
		LocalTime desdePars = LocalTime.parse(desde);
		LocalTime hastaPars = LocalTime.parse(hasta);
		
		Long horasEnGarage = ChronoUnit.HOURS.between(desdePars, hastaPars);
		
		Double total = precio * horasEnGarage;
		
		return total;
	}

	@Override
	public void registrarTicket(Estacionamiento ticket) {
		
		final Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().save(ticket);
	}
	
	 @Override 
	  public List<Garage> consultarGarage() {
		
		  final Session session = sessionFactory.getCurrentSession();
		  
		   List<Garage> listaGarage = session.createCriteria(Garage.class)
				  .list();
				return listaGarage;  
	  }
	 
	 @Override
		public Garage  contultarUnGarage(Garage garage1) {
		 
			final Session session = sessionFactory.getCurrentSession();
			
			return (Garage) session.createCriteria(Garage.class)
					.add(Restrictions.eq("id",garage1.getId()))
					.uniqueResult();
	
			
		}

	@Override
	public Estacionamiento buscarEstacionamientoPorAuto(Auto auto) {
		final Session session = sessionFactory.getCurrentSession();
		return (Estacionamiento) session.createCriteria(Estacionamiento.class)
				.createAlias("auto", "autoBuscado")
				.add(Restrictions.eq("autoBuscado.id", auto.getId()))
				.uniqueResult();
	}
	 
	 
	 
	 
	 
	 
	

}
