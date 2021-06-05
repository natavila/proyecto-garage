package ar.edu.unlam.tallerweb1.servicios;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;

public interface ServicioEstacionamiento {
	void registrarEstacionamiento(Estacionamiento est);

	Estacionamiento buscarEstacionamiento(Long id);
	List<Auto> buscarAutosDeUnGarage(Garage garage1);
	//Boolean asignarAutoaGarage(Garage garage1, Auto auto1);

	HashSet<Auto> buscarAutosQueEstenActivosEnUnGarage(Garage garage1);
}
