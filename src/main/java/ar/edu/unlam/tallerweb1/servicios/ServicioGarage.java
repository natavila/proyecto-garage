package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;

public interface ServicioGarage {

	List<Garage> consultarGarage();
	Boolean agregarGarage(Garage garage1);
	Boolean eliminarGarage(Long id);

	 Garage buscarGarage(Long id);
	

	Garage contultarUnGarage(Garage garage1);
	
	//Boolean asignarAutoaGarage(Garage garage1, Auto auto1);
	public abstract List<Auto> consultarAutosEnGarage(Garage garage1);
	//Boolean sacarAutoDegarage(Auto auto, Garage garage);

	Auto BuscarAutoEnGarage(Auto auto1, Garage garage1);
	



	List <Garage> buscarPorLocalidad(Garage garage1);
	List <Garage> buscarPorPrecioHora(Double precio1, Double precio2);
	List <Garage> buscarPorPrecioMes(Double precio1, Double precio2);
	List <Garage> buscarPorPrecioEstadia(Double precio1, Double precio2);
	void sumarContador(Garage garage);
	
	void restarContador(Garage garage);
	List<Garage> buscarGaragePorLocalidad(String localidad);
	Boolean GarageLleno(Garage garage);
	Integer cantidadDeLugarEnEst(Garage garage);
	ArrayList<Garage> ordenarGaragePorHora();
	ArrayList<Garage> ordenarGaragePorEstadia();
	List <Garage> buscarGarageQueCoincidanConLocalidadDeCliente(Cliente cliente);
	void modificarGarage(Garage garage, Garage modificado);
	
}
