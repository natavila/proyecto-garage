package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlan;

@Service("servicioPlan")
@Transactional
public class ServicioPlanImpl implements ServicioPlan {

	private RepositorioPlan repositorioPlan;

	@Autowired
	public ServicioPlanImpl(RepositorioPlan repositorioPlan) {
		this.repositorioPlan = repositorioPlan;
	}

	@Override
	public void crearPlan(Plan plan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void asignarPlanACliente(Cliente cliente, Plan plan) {
		repositorioPlan.asignarPlanACliente(cliente, plan);

	}

	@Override
	public void darDeBajaPlan(Long id) {
		// TODO Auto-generated method stub

	}

	public List<Plan> obtenerPlanes() {
		return repositorioPlan.listaDePlanes();
	}

}
