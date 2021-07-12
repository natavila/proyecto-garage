package ar.edu.unlam.tallerweb1.servicios;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstacionamiento;

@Service
@Transactional
public class ServicioEstacionamientoImpl implements ServicioEstacionamiento{
			private RepositorioEstacionamiento repositorioEst;
			/*private ServicioGarage servicioGarage;
			private ServicioAuto servicioAuto;
			*/
			@Autowired 
			public ServicioEstacionamientoImpl(RepositorioEstacionamiento repositorioEst, ServicioGarage servicioGarage, ServicioAuto servicioAuto) {
				this.repositorioEst=repositorioEst;
				/*this.servicioGarage = servicioGarage;
				this.servicioAuto = servicioAuto;*/
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

			
			public ArrayList<Auto> buscarAutosQueEstenActivosEnUnGarage(Garage garage1) {
				
				HashSet<Auto> autoLista = new HashSet<Auto>();
				ArrayList<Auto> lista = new ArrayList<Auto>();
				List<Estacionamiento> est = repositorioEst.buscarAutosDeUnGarage(garage1);
				
				for(Estacionamiento e: est) {
					if(e.getActiva().equals(true) && e.getReservado().equals(true) ) {
						lista.add(e.getAuto());
					}
				}
				lista.addAll(autoLista);
				return  lista;
			}

			
			@Override
			public ArrayList<Long> numeroDeTicketAuto(Garage garage1){
				ArrayList<Long> numTickets = new ArrayList<Long>();
				List<Estacionamiento> est = repositorioEst.buscarAutosDeUnGarage(garage1);
				
				for(Estacionamiento e: est) {
					if(e.getActiva().equals(true) && e.getReservado().equals(true)) {
						numTickets.add(e.getId());
					}
				}

				return (ArrayList<Long>) numTickets;
			}
				
			
			
			@Override
			public Estacionamiento buscarEstacionamientoPorAuto(Auto auto) {
				List<Estacionamiento> EstActivos= repositorioEst.buscarEstacionamientoPorAuto(auto);
				Estacionamiento est = new Estacionamiento();
				for(Estacionamiento e: EstActivos) {
					if(e.getActiva().equals(true) && e.getReservado().equals(false)) {
						est = e;
					}
				}
				return   est;


			}
			
			
			@Override 
			public void cambiarEstadoEstacionamiento(Estacionamiento est) {
				Estacionamiento est1 = repositorioEst.consultarEstacionamiento(est);
				if(est1.getActiva().equals(true)) {
					est1.setActiva(false);
			
				}else {
				est.setActiva(true);
				}
			
			}
			
			@Override
			public void meterImagenQr(Estacionamiento est, String img) {
				Estacionamiento est1 = repositorioEst.consultarEstacionamiento(est);
				est1.setImagenQR(img);
			}

			@Override
			public List<Estacionamiento> buscarEstacionamientoPorGarage(Garage garage) {
				
				return repositorioEst.buscarEstacionamientoPorGarage(garage);
			}
			
			
			
			@Override
			public Double dineroGanadoEnElDia(Garage garage) {
				Double suma=0.0;
				
				List<Estacionamiento> est =repositorioEst.consultarEstacionamientoPorFecha();
				for(Estacionamiento e: est) {
					if( garage.getId().equals(e.getGarage1().getId())) {
						suma += e.getPrecioAPagar();
					}
				}
				return suma;
			}
			@Override
			public Double dineroGanadoEnTotal() {
				Double suma=0.0;
				List<Estacionamiento> est =repositorioEst.consultarEstacionamientoPorFecha();
				for(Estacionamiento e: est) {
					
						suma += e.getPrecioAPagar();
					
				}
				return suma;
				
				
			}

			@Override
			public List<Estacionamiento> consultarEstacionamiento() {
				
				return repositorioEst.consultarEstacionamiento();
			}

			@Override
			public List<Estacionamiento> buscarEstacionamientoPorCliente(Cliente cliente) {
				
				return repositorioEst.buscarEstacionamientoPorCliente(cliente);
			}
			
			
			@Override
			public List<Estacionamiento> buscarEstacionamientoPorClienteQueTengaReserva(Cliente cliente){
				ArrayList<Estacionamiento> est = (ArrayList<Estacionamiento>) buscarEstacionamientoPorCliente( cliente);
				ArrayList<Estacionamiento> estac = new ArrayList<Estacionamiento>();
				for(Estacionamiento e: est) {
					if(/*e.getReservado().equals(false) &&*/ e.getAuto().getUsandoGarage().equals(true) && e.getActiva().equals(true)) {
						estac.add(e);
					}
				}
				
				return estac;
			}
			
			@Override
			public void cambiarEstadoDeReserva(Estacionamiento est) {
				Estacionamiento est1 = repositorioEst.consultarEstacionamiento(est);
				if(est.getReservado().equals(false)) {
					est1.setReservado(true);
				}else {
					est1.setReservado(false);
				}
				
			}
			
			//Esto tengo q cambiar
			@Override
			public void ActivarQR(Long idEst) {
				Estacionamiento est = repositorioEst.buscarEstacionamiento(idEst);
				est.setActiva(true);
				
			}
			
			
		
			
			
			
}
