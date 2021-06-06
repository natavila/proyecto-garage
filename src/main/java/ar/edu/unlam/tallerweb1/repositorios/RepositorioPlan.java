package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Plan;

public interface RepositorioPlan {

	public void crearPlan(Plan plan);

	public void asignarPlanACliente(Cliente cliente, Plan plan);

	public void darDeBajaPlan(Long id);

	public List<Plan> listaDePlanes();

}
