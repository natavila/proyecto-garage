package ar.edu.unlam.tallerweb1.repositorios;


import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;

public interface RepositorioGarage {

	List<Garage> consultarGarage();
	Garage EliminarGarage(Long id);
	Boolean agregarGarage(Garage garage1);
	
	//Boolean asignarAutoaGarage(Garage garage1, Auto auto1);
	
	Garage contultarUnGarage(Garage garage1);
	//Boolean sacarAutoDegarage(Auto auto, Garage garage);
	Auto BuscarAutoEnGarage(Auto auto, Garage garage);
	List<Auto> consultarAutosEnGarage(Garage garage1);
	Garage buscarGarage(Long id);
	List <Garage> buscarGarageQueCoincidanConLocalidadDeCliente(Cliente cliente);

	List <Garage> buscarPorLocalidad(Garage garage1);
	List <Garage> buscarPorPrecioHora(Double precio1, Double precio2);
	List <Garage> buscarPorPrecioMes(Double precio1, Double precio2);
	List <Garage> buscarPorPrecioEstadia(Double precio1, Double precio2);
	void modificarDatosGarage(Garage garage, Garage modificado);
	
	
	
}
