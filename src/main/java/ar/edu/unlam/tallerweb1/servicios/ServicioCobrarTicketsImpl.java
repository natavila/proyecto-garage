package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstacionamiento;

@Service
@Transactional
public class ServicioCobrarTicketsImpl implements ServicioCobrarTickets{

	private RepositorioEstacionamiento servicioCobrarTickets;
	
	@Autowired
	public ServicioCobrarTicketsImpl(RepositorioEstacionamiento servicioCobrarTickets){
		this.servicioCobrarTickets = servicioCobrarTickets;
	}

	@Override
	public Long calcularDias(String desde, String hasta) {
		
		return servicioCobrarTickets.calcularDias(desde, hasta);
	}

	@Override
	public Long calcularHoras(String desde, String hasta) {
		
		return servicioCobrarTickets.calcularHoras(desde, hasta);
	}

	@Override
	public Double calcularPrecioPorEstadia(Double precio, Estacionamiento estacionamiento) {
		
		return servicioCobrarTickets.calcularPrecioPorEstadia(precio, estacionamiento);
	}
	
	@Override
	public Double calcularPrecioPorHora(Double precio, String desde, String hasta) {
		
		return servicioCobrarTickets.calcularPrecioPorHora(precio, desde, hasta);
	}

	@Override
	public void registrarTicket(Estacionamiento ticket) {
		
		servicioCobrarTickets.registrarTicket(ticket);
		
	}

	@Override
	public List<Garage> consultarGarage() {
		
		return servicioCobrarTickets.consultarGarage();
	}

	@Override
	public Garage contultarUnGarage(Garage garage1) {
		
		return servicioCobrarTickets.contultarUnGarage(garage1);
	}

	


	
	

}
