<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Garage;

public interface ServicioGarage {

	List<Garage> consultarGarage();
	Boolean agregarGarage(Garage garage1);
	Boolean eliminarGarage(Long id);


	

	Garage contultarUnGarage(Garage garage1);
	
	Boolean asignarAutoaGarage(Garage garage1, Auto auto1);
	public abstract List<Auto> consultarAutosEnGarage(Garage garage1);
	Boolean sacarAutoDegarage(Auto auto, Garage garage);

	Auto BuscarAutoEnGarage(Auto auto1, Garage garage1);
	



	List <Garage> buscarPorLocalidad(Garage garage1);
	List <Garage> buscarPorPrecioHora(Double precio1, Double precio2);
	List <Garage> buscarPorPrecioMes(Double precio1, Double precio2);
	List <Garage> buscarPorPrecioEstadia(Double precio1, Double precio2);
}
=======
package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Garage;

public interface ServicioGarage {

	List<Garage> consultarGarage();
	Boolean agregarGarage(Garage garage1);
	Boolean eliminarGarage(Long id);


	

	Garage contultarUnGarage(Garage garage1);
	
	Boolean asignarAutoaGarage(Garage garage1, Auto auto1);
	public abstract List<Auto> consultarAutosEnGarage(Garage garage1);
	Boolean sacarAutoDegarage(Auto auto, Garage garage);

	Auto BuscarAutoEnGarage(Auto auto1, Garage garage1);
	



	List <Garage> buscarPorLocalidad(Garage garage1);
	List <Garage> buscarPorPrecioHora(Double precio1, Double precio2);
	List <Garage> buscarPorPrecioMes(Double precio1, Double precio2);
	List <Garage> buscarPorPrecioEstadia(Double precio1, Double precio2);
	Garage buscarGaragePorId(Long id);
}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
