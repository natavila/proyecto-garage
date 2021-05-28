<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.repositorios;

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
	public Auto consultarAuto(Auto auto) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Auto) session.createCriteria(Auto.class)
				.add(Restrictions.eq("patente", auto.getPatente()))
				.uniqueResult();
	}

	
}
=======
package ar.edu.unlam.tallerweb1.repositorios;

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
	public Auto consultarAuto(Auto auto) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Auto) session.createCriteria(Auto.class)
				.add(Restrictions.eq("patente", auto.getPatente()))
				.uniqueResult();
	}

	
}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
