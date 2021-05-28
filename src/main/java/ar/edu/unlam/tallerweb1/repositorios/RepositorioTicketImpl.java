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
import ar.edu.unlam.tallerweb1.modelo.Ticket;

@Repository("repositorioTicket")
public class RepositorioTicketImpl implements RepositorioTicket{
	private SessionFactory sessionFactory;
	
	 @Autowired
		public RepositorioTicketImpl(SessionFactory sessionFactory){
			this.sessionFactory = sessionFactory;
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
	public void registrarTicket(Ticket ticket) {
		
		final Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().save(ticket);
	}
	
	 @Override 
	  public List<Garage> consultarGarage() {
		
		  final Session session = sessionFactory.getCurrentSession();
		  
		   List<Garage> listaGarage = session.createCriteria(Garage.class)
				  //.add(Restrictions.eq("nombre", garage1.getNombre()))
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
	public Ticket buscarTicketPertenencienteAlGarage(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Ticket) session.createCriteria(Ticket.class)
				.createAlias("garage", "garageBuscado")
				.createAlias("garageBuscado.id", "garageEncontrado")
				.add(Restrictions.eq("garageEncontrado", id))
				.uniqueResult();
	}
	

}
