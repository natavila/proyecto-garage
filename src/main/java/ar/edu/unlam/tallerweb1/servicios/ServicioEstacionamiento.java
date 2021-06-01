package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;

public interface ServicioEstacionamiento {
	void registrarEstacionamiento(Estacionamiento est);

	Estacionamiento buscarEstacionamiento(Long id);
	//Boolean asignarAutoaGarage(Garage garage1, Auto auto1);
}
