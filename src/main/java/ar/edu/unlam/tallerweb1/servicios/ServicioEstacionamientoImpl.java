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
			
			
			@Autowired 
			public ServicioEstacionamientoImpl(RepositorioEstacionamiento repositorioEst) {
				this.repositorioEst=repositorioEst;
				
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
			public List<Auto> buscarAutosQueEstenActivosEnUnGarage(Garage garage1) {
				List<Auto> autosActivos= buscarAutosDeUnGarage(garage1);
				ArrayList<Auto> autoLista = new ArrayList<Auto>();
				
				for(Auto e: autosActivos) {
					if(e.getUsandoGarage()==true) {
						autoLista.add(e);
					}
					
				}
				
				return (List<Auto>) autoLista;
			}
			

			/*@Override
			public Boolean asignarAutoaGarage(Garage garage1, Auto auto1) {
				
				return repositorioEst.asignarAutoaGarage(garage1, auto1);
			}
			
			
		*/
}
