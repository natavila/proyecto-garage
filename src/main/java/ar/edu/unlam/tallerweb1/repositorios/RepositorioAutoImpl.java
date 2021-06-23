package ar.edu.unlam.tallerweb1.repositorios;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistroImpl;

@Repository("repositorioAuto")
public class RepositorioAutoImpl implements RepositorioAuto{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioAutoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
    
	@Override
		public void registrarAuto(Auto auto) { 
	    		
				final Session session = sessionFactory.getCurrentSession();
				session.save(auto);	
		}
	
	
	
	
	@Override
	public void eliminarAuto(Auto auto) {
		final Session session = sessionFactory.getCurrentSession();
		Auto auto1=consultarAuto(auto);
		auto1.setEnUso(false);
	}
	
	@Override
	public void cambiarEstadoDeUso(Auto auto) {
		final Session session = sessionFactory.getCurrentSession();
		Auto auto1=consultarAuto(auto);
		if(auto1.getEnUso().equals(false)) {
			auto1.setEnUso(true);
		}else {
			auto1.setEnUso(false);
		}
	}
	
	@Override
	public List<Auto> listaDeAutos() {
		
		final Session session = sessionFactory.getCurrentSession();
		  
		   List<Auto> listaAutos = session.createCriteria(Auto.class)
				  .list();
				return listaAutos;  
	}

	@Override
	public Auto consultarAuto(Auto auto) {
		final Session session = sessionFactory.getCurrentSession();
		return (Auto) session.createCriteria(Auto.class)
				.add(Restrictions.eq("patente", auto.getPatente()))
				.uniqueResult();
	}
	
	

	@Override
	public void cambiarEstadoDeSiestaEnGarageOno(Auto auto) {
		final Session session = sessionFactory.getCurrentSession();
			Auto auto1 =	(Auto) session.createCriteria(Auto.class)
				.add(Restrictions.eq("patente", auto.getPatente()))
				.uniqueResult();
			
			if(auto1.getUsandoGarage().equals(false)) {
				auto1.setUsandoGarage(true);
			}else {
				if(auto1.getUsandoGarage().equals(true)){
			}
				auto1.setUsandoGarage(false);
			}
			
	}
	
	




	@Override
	public List<Auto> ConsultarAutoDeCliente(Cliente cliente) {
		final Session session = sessionFactory.getCurrentSession();
		List <Auto> lista =  session.createCriteria(Auto.class)
				.createAlias("cliente", "clienteBuscado")
				.add(Restrictions.eq("clienteBuscado.id", cliente.getId()))
				.list();		
		return lista;
		
	}
	
	@Override
	public Auto buscarAuto(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return  (Auto) session.createCriteria(Auto.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}






	

	
}
