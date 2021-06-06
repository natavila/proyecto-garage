package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocalidad;

@Service
@Transactional
public class ServicioLocalidadImp implements ServicioLocalidad {
private RepositorioLocalidad repositorioLocalidad;
private List<String> listaLoc;
@Autowired
public ServicioLocalidadImp(RepositorioLocalidad repositorioLocalidad) {
	this.repositorioLocalidad = repositorioLocalidad;
}
	

@Override
public Boolean agregarLocalidad (Localidad loc) {
	return repositorioLocalidad.agregarLocalidad(loc);
}

@Override
public Boolean eliminarLocalidad (Long loc) {
	return repositorioLocalidad.EliminarLocalidad(loc);
}


@Override 
public  List<Localidad> consultarLocalidad(){
	return repositorioLocalidad.consultarLocalidad();
}

@Override
public Localidad consultarUnaLocalidad(Localidad loc) {
	return repositorioLocalidad.consultarUnaLocalidad(loc);
	
}


@Override
public Boolean verificarSiExisteLocalidad(Localidad loc) {
	List<Localidad> lista =  consultarLocalidad();
	Boolean repetido= false;
	for(Localidad e: lista) {
		if(e.getLocalidad().equals(loc.getLocalidad())) {
			repetido = true;
			return repetido;
		}else {
			repetido = false;
		}
	}
	return repetido;
}




@Override
public List<String> devolverNombresDeLocalidades(){
	List<Localidad> lista = consultarLocalidad();
	ArrayList<String> listaLoc = new ArrayList<String>();
	for(Localidad e: lista) { 
		  listaLoc.add(e.getLocalidad());
	}
	return  listaLoc;
}

	
}
