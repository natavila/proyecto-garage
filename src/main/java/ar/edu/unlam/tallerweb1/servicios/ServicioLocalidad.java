package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Localidad;

public interface ServicioLocalidad {

	Boolean agregarLocalidad(Localidad loc);

	Boolean eliminarLocalidad(Long loc);

	List<Localidad> consultarLocalidad();

	Localidad consultarUnaLocalidad(Localidad loc);

	Boolean verificarSiExisteLocalidad(Localidad loc);



	List<String> devolverNombresDeLocalidades();

	

}
