package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;

public interface RepositorioEstacionamiento {

	Long calcularDias(String desde, String hasta);
	
	Long calcularHoras(String desde, String hasta);
	
	Double calcularPrecioPorEstadia(Double precio, String desde, String hasta);
	
	Double calcularPrecioPorHora(Double precio, String desde, String hasta);
	
	void registrarTicket(Estacionamiento ticket);
	
	List<Garage> consultarGarage();
	
	Garage contultarUnGarage(Garage garage1);
	
	void registrarEstacionamiento(Estacionamiento est);

	Estacionamiento buscarEstacionamiento(Long id);
	//Boolean asignarAutoaGarage(Garage garage1, Auto auto1);
	List<Estacionamiento> buscarAutosDeUnGarage(Garage garage1);
}
