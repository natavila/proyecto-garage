package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstacionamiento;

@Service
@Transactional
public class ServicioEstacionamientoImpl implements ServicioEstacionamiento{
			private RepositorioEstacionamiento repositorioEst;
			private ServicioGarage servicioGarage;
			private ServicioAuto servicioAuto;
			
			@Autowired 
			public ServicioEstacionamientoImpl(RepositorioEstacionamiento repositorioEst, ServicioGarage servicioGarage, ServicioAuto servicioAuto) {
				this.repositorioEst=repositorioEst;
				this.servicioGarage = servicioGarage;
				this.servicioAuto = servicioAuto;
			}

			@Override
			public void registrarEstacionamiento(Estacionamiento est) {
				repositorioEst.registrarEstacionamiento(est);
				
			}

			@Override
			public Estacionamiento buscarEstacionamiento(Long id) {
				
				return repositorioEst.buscarEstacionamiento(id);
			}

			@Override
			public List<Auto> buscarAutosDeUnGarage(Garage garage1) {
				
				ArrayList<Auto> autoLista = new ArrayList<Auto>();
				List<Estacionamiento> est = repositorioEst.buscarAutosDeUnGarage(garage1);
				
				for(Estacionamiento e: est) {
	
					autoLista.add(e.getAuto());
				}
				
				return (List<Auto>) autoLista;
				
			}
			
			@Override

			
			public HashSet<Auto> buscarAutosQueEstenActivosEnUnGarage(Garage garage1) {
				//List<Auto> autosActivos= buscarAutosDeUnGarage(garage1);
				HashSet<Auto> autoLista = new HashSet<Auto>();

				
				List<Estacionamiento> est = repositorioEst.buscarAutosDeUnGarage(garage1);
				
				for(Estacionamiento e: est) {
					if(e.getActiva().equals(true) ) {
						autoLista.add(e.getAuto());
					}
				}

				
				return (HashSet<Auto>) autoLista;
			}

			
			@Override
			public Estacionamiento buscarEstacionamientoPorAuto(Auto auto) {
				List<Estacionamiento> EstActivos= repositorioEst.buscarEstacionamientoPorAuto(auto);
				Estacionamiento est = new Estacionamiento();
				for(Estacionamiento e: EstActivos) {
					if(e.getActiva().equals(true)) {
						est = e;
					}
				}
				return   est;


			}
			
			
			@Override 
			public void cambiarEstadoEstacionamiento(Estacionamiento est) {
				
				if(est.getActiva().equals(true)) {
					repositorioEst.cambiarEstadoEstacionamiento(est);
			
			}else {
				
			}
			
			}
}
