package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGarage;

@Service
@Transactional
public class ServicioGarageImpl implements ServicioGarage{

private RepositorioGarage repositorioGarage;
	
	@Autowired
	public ServicioGarageImpl(RepositorioGarage repositorioGarage) {
		this.repositorioGarage = repositorioGarage;
	}
	
	@Override
	public Boolean agregarGarage(Garage garage1) {
		return repositorioGarage.agregarGarage(garage1);
	}
	
	@Override
	public Boolean eliminarGarage(Long id) {
		return repositorioGarage.EliminarGarage(id);
	}
	
	@Override
	public List<Garage> consultarGarage() {
		
		return repositorioGarage.consultarGarage();
	}
	
	@Override
	public Boolean asignarAutoaGarage(Garage garage1, Auto auto1) {
		
		return repositorioGarage.asignarAutoaGarage(garage1, auto1);
	}

	@Override
	public Garage contultarUnGarage(Garage garage1) {
		
		return repositorioGarage.contultarUnGarage(garage1);
	}

	@Override
	public List<Auto> consultarAutosEnGarage(Garage garage1) {
		
		return repositorioGarage.consultarAutosEnGarage(garage1);
	}

	@Override
	public Boolean sacarAutoDegarage(Auto auto, Garage garage) {
		
		return repositorioGarage.sacarAutoDegarage(auto, garage);
	}	

	@Override
	public List<Garage> buscarPorPrecioHora(Double precio1, Double precio2) {
		// TODO Auto-generated method stub
		return repositorioGarage.buscarPorPrecioHora(precio1, precio2);
	}

	@Override
	public List<Garage> buscarPorPrecioMes(Double precio1, Double precio2) {
		// TODO Auto-generated method stub
		return repositorioGarage.buscarPorPrecioMes(precio1, precio2);
	}

	@Override
	public List<Garage> buscarPorPrecioEstadia(Double precio1, Double precio2) {
		// TODO Auto-generated method stub
		return repositorioGarage.buscarPorPrecioEstadia(precio1, precio2);
	}

	@Override
	public List<Garage> buscarPorLocalidad(Garage garage1) {
		// TODO Auto-generated method stub
		return repositorioGarage.buscarPorLocalidad(garage1);
	}

	@Override
	public Auto BuscarAutoEnGarage(Auto auto1, Garage garage1) {
		
		return repositorioGarage.BuscarAutoEnGarage(auto1, garage1);
	}
	

	
}
