package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario{

	private SessionFactory sessionFactory;
	
	@Autowired 
	public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Usuario consultarUsuarioPorId(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("id", usuario.getId()))
				.uniqueResult();
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		
		final Session session = sessionFactory.getCurrentSession();
		session.save(usuario);
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}

}
