package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstacionamiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGarage;


@Service
@Transactional
public class ServicioGarageImpl implements ServicioGarage, Comparator<Garage>{
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
		Garage garage = repositorioGarage.EliminarGarage(id);
		if(garage.getActivo().equals(true)) {
			garage.setActivo(false);
			return true;
			
		}else {
			return false;
		}
		
		
	}
	
	@Override
	public List<Garage> consultarGarage() {
		ArrayList<Garage> listaActivos = new ArrayList<Garage>();
		
		for(Garage e: repositorioGarage.consultarGarage()) {
			if(e.getActivo() == true) {
				listaActivos.add(e);
			}
		}
		return listaActivos;
	}
	
	
	@Override
	public ArrayList<Garage> ordenarGaragePorHora(){
		ArrayList<Garage> listaPorHora = new ArrayList<Garage>();
		listaPorHora.addAll(repositorioGarage.consultarGarage());
		Collections.sort(listaPorHora, new Comparator<Garage>() {
			@Override
			public int compare(Garage g1, Garage g2) {
				return new Integer (g1.getPrecioHora().compareTo(g2.getPrecioHora()));
			}
		});
		
		return listaPorHora;
	}

	
	@Override
	public ArrayList<Garage> ordenarGaragePorEstadia(){
		ArrayList<Garage> listaPorEst= new ArrayList<Garage>();
		listaPorEst.addAll(repositorioGarage.consultarGarage());
		Collections.sort(listaPorEst, new Comparator<Garage>() {
			@Override
			public int compare(Garage g1, Garage g2) {
				return new Integer (g1.getPrecioEstadia().compareTo(g2.getPrecioEstadia()));
			}
		});
		
		return listaPorEst;
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
	public List<Garage> buscarPorPrecioHora(Double precio1, Double precio2) {
		
		return repositorioGarage.buscarPorPrecioHora(precio1, precio2);
	}

	@Override
	public List<Garage> buscarPorPrecioMes(Double precio1, Double precio2) {
		// TODO Auto-generated method stub
		return repositorioGarage.buscarPorPrecioMes(precio1, precio2);
	}

	@Override
	public List<Garage> buscarPorPrecioEstadia(Double precio1, Double precio2) {
		
		return repositorioGarage.buscarPorPrecioEstadia(precio1, precio2);
	}

	@Override
	public List<Garage> buscarPorLocalidad(Garage garage1) {
		
		return repositorioGarage.buscarPorLocalidad(garage1);
	}

	@Override
	public Auto BuscarAutoEnGarage(Auto auto1, Garage garage1) {
		
		return repositorioGarage.BuscarAutoEnGarage(auto1, garage1);
	}

	@Override
	public Garage buscarGarage(Long id) {
		
		return repositorioGarage.buscarGarage(id);
	}

	
	@Override 
	public List<Garage> buscarGaragePorLocalidad (String localidad ){
		ArrayList<Garage> lista = (ArrayList<Garage>) repositorioGarage.consultarGarage();
		ArrayList<Garage> listaLoc = new ArrayList<Garage>();
		for(Garage e: lista) {
			if(e.getLocalidad().equals(localidad)) {
				listaLoc.add(e);
			}
		}
		
		return listaLoc;
	}
	
	@Override
	public Boolean GarageLleno(Garage garage) {
		if(garage.getCapacidad()<=garage.getContador()) {
		return true;
		}else {
			return false;
		}
	}
	
	
	@Override 

	public void sumarContador(Garage garage) {
		
		Garage garage1 = repositorioGarage.contultarUnGarage(garage);
		garage1.setContador(garage1.getContador()+1);
	}
	
	
	
	@Override
	public void restarContador(Garage garage) {
		
		
		Garage garage1 = repositorioGarage.contultarUnGarage(garage);
		
		garage1.setContador(garage1.getContador()-1);
		
		if(garage1.getContador()<0) {
			garage1.setContador(0);
		}

	}


	
	@Override 
	public Integer cantidadDeLugarEnEst(Garage garage) {
		Garage garage1 = repositorioGarage.contultarUnGarage(garage);
		return (garage1.getCapacidad()-garage1.getContador());
		
	}

	@Override
	public int compare(Garage o1, Garage o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Garage> buscarGarageQueCoincidanConLocalidadDeCliente(Cliente cliente) {
	
		return repositorioGarage.buscarGarageQueCoincidanConLocalidadDeCliente(cliente);
	}
	
	@Override
	public void modificarGarage(Garage garage, Garage modificado) {
		repositorioGarage.modificarDatosGarage(garage, modificado);
	}
	

}
