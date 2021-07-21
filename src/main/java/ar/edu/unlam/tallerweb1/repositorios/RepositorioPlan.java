package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Plan;

public interface RepositorioPlan {

	public void crearPlan(Plan plan);

	public void darDeBajaPlan(Long id);

	public List<Plan> listaDePlanes();

	public Plan consultarPlan(Long id);

	public Plan existeClienteConPlan(Cliente cliente, Plan plan);

	void asignarPlanACliente(Cliente cliente, Plan plan);

	void actualizarHorasReservaEstadia(Cliente cliente, Estacionamiento estacionamiento);
	
	void actualizarHorasReservaHora(Cliente cliente, Estacionamiento estacionamiento);
	
	void actualizarCantidadDeAutosPlan(Cliente cliente);
	
	void actualizarPagoDeReserva(Estacionamiento estacionamiento);

}
