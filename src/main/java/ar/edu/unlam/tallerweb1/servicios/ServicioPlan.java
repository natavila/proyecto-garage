package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Plan;

public interface ServicioPlan {

	public void crearPlan(Plan plan);

	public void asignarPlanACliente(Cliente cliente, Plan plan);

	public void darDeBajaPlan(Long id);

	public List<Plan> obtenerPlanes();

	public Plan consultarPlan(Long id);
	
	public Plan existeClienteConPlan(Cliente cliente, Plan plan);

	void actualizarEstadoPlan(Cliente cliente, Long horas);
	
	public void actualizarPagoDeReserva(Estacionamiento estacionamiento);
	
}
