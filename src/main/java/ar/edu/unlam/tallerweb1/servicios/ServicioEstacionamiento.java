package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;

public interface ServicioEstacionamiento {
	void registrarEstacionamiento(Estacionamiento est);

	Estacionamiento buscarEstacionamiento(Long id);
	Estacionamiento buscarEstacionamientoPorAuto(Auto auto);
	List<Auto> buscarAutosDeUnGarage(Garage garage1);
	List <Estacionamiento> buscarEstacionamientoPorCliente(Cliente cliente);
	//Boolean asignarAutoaGarage(Garage garage1, Auto auto1);


	ArrayList<Auto> buscarAutosQueEstenActivosEnUnGarage(Garage garage1);

	List<Estacionamiento> buscarEstacionamientoPorGarage(Garage garage);

	void cambiarEstadoEstacionamiento(Estacionamiento est);
	List<Estacionamiento> consultarEstacionamiento();
	Double dineroGanadoEnElDia(Garage garage);

	Double dineroGanadoEnTotal();

	ArrayList<Long> numeroDeTicketAuto(Garage garage1);

	void ActivarQR(Long idEst);

}
