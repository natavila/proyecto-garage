package ar.edu.unlam.tallerweb1.servicios;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstacionamiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGarage;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlan;

@Service("servicioPlan")
@Transactional
public class ServicioPlanImpl implements ServicioPlan {

	private RepositorioPlan repositorioPlan;
	private RepositorioCliente repositorioCliente;
	private ServicioPlan servicioPlan;
	private ServicioCobrarTickets servicioCobrarTickets;
	private RepositorioEstacionamiento RepositorioEst;
	
	@Autowired
	public ServicioPlanImpl(RepositorioPlan repositorioPlan) {
		this.repositorioPlan = repositorioPlan;
	}

	@Override
	public void crearPlan(Plan plan) {
		repositorioPlan.crearPlan(plan);
	}

	@Override
	public void asignarPlanACliente(Cliente cliente, Plan plan) {

		repositorioPlan.asignarPlanACliente(cliente, plan);

	}

	@Override
	public void darDeBajaPlan(Long id) {
		repositorioPlan.darDeBajaPlan(id);

	}

	public List<Plan> obtenerPlanes() {
		return repositorioPlan.listaDePlanes();
	}

	@Override
	public Plan consultarPlan(Long id) {
		return repositorioPlan.consultarPlan(id);

	}
	
	@Override
	public Plan existeClienteConPlan(Cliente cliente, Plan plan) {
		return repositorioPlan.existeClienteConPlan(cliente, plan);
	}

	
	@Override
	public void actualizarEstadoPlan(Cliente cliente, Long horas) {
		cliente.actualizarEstado(horas);
		repositorioPlan.actualizarPlan(cliente.getPlan());
	}

	@Override
	public void actualizarPagoDeReserva(Estacionamiento estacionamiento) {
		repositorioPlan.actualizarPagoDeReserva(estacionamiento);
		
	}



}
