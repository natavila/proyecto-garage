package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Plan;

@Repository("repositorioPlan")
public class RepositorioPlanImpl implements RepositorioPlan {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioPlanImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void crearPlan(Plan plan) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(plan);
	}

	
	@Override
	public void asignarPlanACliente(Cliente cliente, Plan plan) {
		final Session session = sessionFactory.getCurrentSession();
		Cliente cliente1=  (Cliente) session.createCriteria(Cliente.class)
				.add(Restrictions.eq("id",cliente.getId()))
				.uniqueResult();
		         cliente1.setPlan(plan);
		         cliente1.setCantidadAutosRestantes(plan.getCantidadAutosPermitidos());
		         cliente1.setCantidadHorasRestantes(plan.getCantidadHorasPermitidas());
	}
	
	@Override
	public Plan existeClienteConPlan(Cliente cliente, Plan plan) {
		final Session session = sessionFactory.getCurrentSession();
		return (Plan) session.createCriteria(Plan.class)
				.createAlias("cliente", "clienteBuscado")
				.createAlias("plan", "planBuscado")
				.add(Restrictions.eq("usuarioBuscado.id", cliente.getId()))
				.add(Restrictions.eq("planBuscado.id", plan.getId()))
				.uniqueResult();
	}

	@Override
	public void darDeBajaPlan(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Plan plan1 = (Plan) session.createCriteria(Plan.class).add(Restrictions.eq("id", id)).uniqueResult();
		sessionFactory.getCurrentSession().delete(plan1);

	}

	@Override
	public List<Plan> listaDePlanes() {
		final Session session = sessionFactory.getCurrentSession();

		return session.createCriteria(Plan.class).list();

			}



	@Override
	public Plan consultarPlan(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Plan) session.createCriteria(Plan.class).add(Restrictions.eq("id", id)).uniqueResult();

	}

	@Override
	public void actualizarPlan(Plan plan) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(plan);
		
		
	}

	@Override
	public void actualizarPagoDeReserva(Estacionamiento estacionamiento) {
		final Session session = sessionFactory.getCurrentSession();
		estacionamiento.setEstaPagado(true);
		session.update(estacionamiento);
	}


}
