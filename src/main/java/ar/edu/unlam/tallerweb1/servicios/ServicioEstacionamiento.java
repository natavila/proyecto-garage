package ar.edu.unlam.tallerweb1.servicios;

import java.util.HashSet;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;

public interface ServicioEstacionamiento {
	void registrarEstacionamiento(Estacionamiento est);

	Estacionamiento buscarEstacionamiento(Long id);
	Estacionamiento buscarEstacionamientoPorAuto(Auto auto);
	List<Auto> buscarAutosDeUnGarage(Garage garage1);
	//Boolean asignarAutoaGarage(Garage garage1, Auto auto1);


	HashSet<Auto> buscarAutosQueEstenActivosEnUnGarage(Garage garage1);

	List<Estacionamiento> buscarEstacionamientoPorGarage(Garage garage);

	void cambiarEstadoEstacionamiento(Estacionamiento est);
	List<Estacionamiento> consultarEstacionamiento();
	Double dineroGanadoEnElDia(Garage garage);

}
