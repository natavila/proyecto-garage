package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface RepositorioAuto {

	public Auto consultarAuto(Auto auto);

	List<Auto> listaDeAutos();

	void registrarAuto(Auto auto);

	List<Auto> ConsultarAutoDeCliente(Cliente cliente);
	
	List<Auto> consultarAutosSinGarage();
	
	List<Auto> consultarAutosSinGarageDeCliente(Cliente cliente);

	Auto buscarAuto(Long id);

	void eliminarAuto(Auto auto);

	void cambiarEstadoDeUso(Auto auto);

	//List<Auto> listaDeAutosDeClientesAfueraDeEst(Cliente cliente);
	
	
}
