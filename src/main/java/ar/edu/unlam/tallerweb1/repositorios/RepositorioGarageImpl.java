<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import ar.edu.unlam.tallerweb1.modelo.Auto;
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
		public Garage  contultarUnGarage(Garage garage1) {
			final Session session = sessionFactory.getCurrentSession();
			
			return (Garage) session.createCriteria(Garage.class)
					.add(Restrictions.eq("id",garage1.getId()))
					.uniqueResult();
		}
	@Override 
	public Boolean agregarGarage(Garage garage1) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		sessionFactory.getCurrentSession().save(garage1);
		return true;
	}

	@Override
	public Boolean EliminarGarage(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Garage garage1 = (Garage) session.createCriteria(Garage.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
		sessionFactory.getCurrentSession().delete(garage1);
		return true;
	}


	@Override
	public Boolean asignarAutoaGarage(Garage garage1, Auto auto1) {
		RepositorioClienteImpl repo2 = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv2 = new ServicioRegistroImpl(repo2);
		
		final Session session = sessionFactory.getCurrentSession();
		
		Boolean agrego= false;
		Garage g2= contultarUnGarage( garage1);
		Auto a2 = serv2.consultarAuto(auto1) ;
		if(g2!=null && a2 !=null && g2.getCapacidad()>g2.getContador()) {
			
			a2.setAlquiler(g2);;
			g2.setContador(g2.getContador()+1);
			
			serv2.consultarAuto(auto1).setGarage(g2);
			//contultarUnGarage( garage1).setContador(contultarUnGarage( garage1).getContador()+1);
			
			
			agrego = true;	
		}else {
			agrego= false;
			
		}
		return agrego;

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
	
	@Override
	//Falta Hacer
	public Auto BuscarAutoEnGarage(Auto auto1, Garage garage1) {
		RepositorioClienteImpl repo2 = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv2 = new ServicioRegistroImpl(repo2);

		List<Auto> lista = consultarAutosEnGarage(garage1);
		Auto buscado = new Auto();
		for(Auto auto: lista) {
			if(auto.getId().equals(auto1.getId())) {
				buscado = auto ;
			}else {
				buscado= null;
			}
		}
	
		return buscado;
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
	public Boolean sacarAutoDegarage(Auto auto, Garage garage) {
		//List<Auto> lista = consultarAutosEnGarage(garage);
		
		RepositorioClienteImpl repo2 = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv2 = new ServicioRegistroImpl(repo2);
		
		final Session session = sessionFactory.getCurrentSession();
		Boolean salio= false;
		Auto buscado=BuscarAutoEnGarage( auto,  garage);
		Garage garage1 = contultarUnGarage(garage);
		if(buscado!=null && garage1 !=null) {
			buscado.setGarage(null);
			garage1.setContador(garage1.getContador()-1);
			salio = true;	
		}else {
			salio= false;
			
		}
		return salio;

	}
		
	
	
	
	 
	 
}
=======
package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import ar.edu.unlam.tallerweb1.modelo.Auto;
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
		public Garage  contultarUnGarage(Garage garage1) {
			final Session session = sessionFactory.getCurrentSession();
			
			return (Garage) session.createCriteria(Garage.class)
					.add(Restrictions.eq("id",garage1.getId()))
					.uniqueResult();
		}
	@Override 
	public Boolean agregarGarage(Garage garage1) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		sessionFactory.getCurrentSession().save(garage1);
		return true;
	}

	@Override
	public Boolean EliminarGarage(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Garage garage1 = (Garage) session.createCriteria(Garage.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
		sessionFactory.getCurrentSession().delete(garage1);
		return true;
	}


	@Override
	public Boolean asignarAutoaGarage(Garage garage1, Auto auto1) {
		RepositorioClienteImpl repo2 = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv2 = new ServicioRegistroImpl(repo2);
		
		final Session session = sessionFactory.getCurrentSession();
		
		Boolean agrego= false;
		Garage g2= contultarUnGarage( garage1);
		Auto a2 = serv2.consultarAuto(auto1) ;
		if(g2!=null && a2 !=null && g2.getCapacidad()>g2.getContador()) {
			
			a2.setGarage(g2);
			g2.setContador(g2.getContador()+1);
			
			serv2.consultarAuto(auto1).setGarage(g2);
			//contultarUnGarage( garage1).setContador(contultarUnGarage( garage1).getContador()+1);
			
			
			agrego = true;	
		}else {
			agrego= false;
			
		}
		return agrego;

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
	
	@Override
	//Falta Hacer
	public Auto BuscarAutoEnGarage(Auto auto1, Garage garage1) {
		RepositorioClienteImpl repo2 = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv2 = new ServicioRegistroImpl(repo2);

		List<Auto> lista = consultarAutosEnGarage(garage1);
		Auto buscado = new Auto();
		for(Auto auto: lista) {
			if(auto.getId().equals(auto1.getId())) {
				buscado = auto ;
			}else {
				buscado= null;
			}
		}
	
		return buscado;
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
	public Boolean sacarAutoDegarage(Auto auto, Garage garage) {
		//List<Auto> lista = consultarAutosEnGarage(garage);
		
		RepositorioClienteImpl repo2 = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv2 = new ServicioRegistroImpl(repo2);
		
		final Session session = sessionFactory.getCurrentSession();
		Boolean salio= false;
		Auto buscado=BuscarAutoEnGarage( auto,  garage);
		Garage garage1 = contultarUnGarage(garage);
		if(buscado!=null && garage1 !=null) {
			buscado.setGarage(null);
			garage1.setContador(garage1.getContador()-1);
			salio = true;	
		}else {
			salio= false;
			
		}
		return salio;

	}

	@Override
	public Garage buscarGaragePorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(Garage.class, id);
		
	}
		
	
	
	
	 
	 
}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import ar.edu.unlam.tallerweb1.modelo.Auto;
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
		public Garage  contultarUnGarage(Garage garage1) {
			final Session session = sessionFactory.getCurrentSession();
			
			return (Garage) session.createCriteria(Garage.class)
					.add(Restrictions.eq("id",garage1.getId()))
					.uniqueResult();
		}
	@Override 
	public Boolean agregarGarage(Garage garage1) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		sessionFactory.getCurrentSession().save(garage1);
		return true;
	}

	@Override
	public Boolean EliminarGarage(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Garage garage1 = (Garage) session.createCriteria(Garage.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
		sessionFactory.getCurrentSession().delete(garage1);
		return true;
	}


	@Override
	public Boolean asignarAutoaGarage(Garage garage1, Auto auto1) {
		RepositorioClienteImpl repo2 = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv2 = new ServicioRegistroImpl(repo2);
		
		final Session session = sessionFactory.getCurrentSession();
		
		Boolean agrego= false;
		Garage g2= contultarUnGarage( garage1);
		Auto a2 = serv2.consultarAuto(auto1) ;
		if(g2!=null && a2 !=null && g2.getCapacidad()>g2.getContador()) {
			
			a2.setGarage(g2);
			g2.setContador(g2.getContador()+1);
			
			serv2.consultarAuto(auto1).setGarage(g2);
			//contultarUnGarage( garage1).setContador(contultarUnGarage( garage1).getContador()+1);
			
			
			agrego = true;	
		}else {
			agrego= false;
			
		}
		return agrego;

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
	
	@Override
	//Falta Hacer
	public Auto BuscarAutoEnGarage(Auto auto1, Garage garage1) {
		RepositorioClienteImpl repo2 = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv2 = new ServicioRegistroImpl(repo2);

		List<Auto> lista = consultarAutosEnGarage(garage1);
		Auto buscado = new Auto();
		for(Auto auto: lista) {
			if(auto.getId().equals(auto1.getId())) {
				buscado = auto ;
			}else {
				buscado= null;
			}
		}
	
		return buscado;
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
	public Boolean sacarAutoDegarage(Auto auto, Garage garage) {
		//List<Auto> lista = consultarAutosEnGarage(garage);
		
		RepositorioClienteImpl repo2 = new RepositorioClienteImpl(sessionFactory);
		ServicioRegistroImpl serv2 = new ServicioRegistroImpl(repo2);
		
		final Session session = sessionFactory.getCurrentSession();
		Boolean salio= false;
		Auto buscado=BuscarAutoEnGarage( auto,  garage);
		Garage garage1 = contultarUnGarage(garage);
		if(buscado!=null && garage1 !=null) {
			buscado.setGarage(null);
			garage1.setContador(garage1.getContador()-1);
			salio = true;	
		}else {
			salio= false;
			
		}
		return salio;

	}
		
	
	
	
	 
	 
}
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
