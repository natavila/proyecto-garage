package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;

import antlr.collections.List;
import ar.edu.unlam.tallerweb1.modelo.Localidad;

public interface ServicioLocalidad {

	

	Boolean agregarLocalidad(Localidad loc);

	Boolean eliminarLocalidad(Long loc);

	ArrayList<Localidad> consultarLocalidad();

	Localidad consultarUnaLocalidad(Localidad loc);

	Boolean verificarSiExisteLocalidad(Localidad loc);

	java.util.List<String> devolverNombresDeLocalidades();
}
