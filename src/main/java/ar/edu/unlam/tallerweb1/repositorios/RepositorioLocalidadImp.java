package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




import ar.edu.unlam.tallerweb1.modelo.Localidad;

@Repository("repositorioLocalidad")
public class RepositorioLocalidadImp implements RepositorioLocalidad {
	
	private SessionFactory sessionFactory;
	
	 @Autowired
		public RepositorioLocalidadImp(SessionFactory sessionFactory){
			this.sessionFactory = sessionFactory;
	 }
@Override 
public Boolean agregarLocalidad (Localidad loc) {
	final Session session = sessionFactory.getCurrentSession();
	sessionFactory.getCurrentSession().save(loc);
	return true;
}
	 

@Override
public Boolean EliminarLocalidad(Long id) {
	final Session session = sessionFactory.getCurrentSession();
	Localidad loc =   (Localidad) session.createCriteria(Localidad.class)
			.add(Restrictions.eq("id",id))
			.uniqueResult();
	sessionFactory.getCurrentSession().delete(id);
	return true;
}

@Override

public List<Localidad> consultarLocalidad() {
	
	  final Session session =  sessionFactory.getCurrentSession();
	  
	   List<Localidad> listaDeLocalidades = session.createCriteria(Localidad.class)
			  .list();
			return listaDeLocalidades;  
}


@Override
public Localidad consultarUnaLocalidad(Localidad loc) {
	final Session session = sessionFactory.getCurrentSession();
	return (Localidad) session.createCriteria(Localidad.class)
			.add(Restrictions.eq("id",loc.getId()))
			.uniqueResult();
}
	
	
}
