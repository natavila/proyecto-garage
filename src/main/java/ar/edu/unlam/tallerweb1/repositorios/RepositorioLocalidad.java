package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Localidad;

public interface RepositorioLocalidad {

	Boolean agregarLocalidad(Localidad loc);

	Boolean EliminarLocalidad(Long id);

	List<Localidad> consultarLocalidad();

	Localidad consultarUnaLocalidad(Localidad loc);

}
