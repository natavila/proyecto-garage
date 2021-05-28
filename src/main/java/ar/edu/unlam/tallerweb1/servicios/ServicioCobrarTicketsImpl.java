<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Ticket;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTicket;

@Service
@Transactional
public class ServicioCobrarTicketsImpl implements ServicioCobrarTickets{

	private RepositorioTicket servicioCobrarTickets;
	
	@Autowired
	public ServicioCobrarTicketsImpl(RepositorioTicket servicioCobrarTickets){
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
	public Double calcularPrecioPorEstadia(Double precio, String desde, String hasta) {
		
		return servicioCobrarTickets.calcularPrecioPorEstadia(precio, desde, hasta);
	}
	
	@Override
	public Double calcularPrecioPorHora(Double precio, String desde, String hasta) {
		
		return servicioCobrarTickets.calcularPrecioPorHora(precio, desde, hasta);
	}

	@Override
	public void registrarTicket(Ticket ticket) {
		
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
=======
package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Ticket;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTicket;

@Service
@Transactional
public class ServicioCobrarTicketsImpl implements ServicioCobrarTickets{

	private RepositorioTicket servicioCobrarTickets;
	
	@Autowired
	public ServicioCobrarTicketsImpl(RepositorioTicket servicioCobrarTickets){
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
	public Double calcularPrecioPorEstadia(Double precio, String desde, String hasta) {
		
		return servicioCobrarTickets.calcularPrecioPorEstadia(precio, desde, hasta);
	}
	
	@Override
	public Double calcularPrecioPorHora(Double precio, String desde, String hasta) {
		
		return servicioCobrarTickets.calcularPrecioPorHora(precio, desde, hasta);
	}

	@Override
	public void registrarTicket(Ticket ticket) {
		
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

	@Override
	public Ticket buscarTicketPertenencienteAlGarage(Long id) {
		
		return servicioCobrarTickets.buscarTicketPertenencienteAlGarage(id);
	}

	


	
	

}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Ticket;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTicket;

@Service
@Transactional
public class ServicioCobrarTicketsImpl implements ServicioCobrarTickets{

	private RepositorioTicket servicioCobrarTickets;
	
	@Autowired
	public ServicioCobrarTicketsImpl(RepositorioTicket servicioCobrarTickets){
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
	public Double calcularPrecioPorEstadia(Double precio, String desde, String hasta) {
		
		return servicioCobrarTickets.calcularPrecioPorEstadia(precio, desde, hasta);
	}
	
	@Override
	public Double calcularPrecioPorHora(Double precio, String desde, String hasta) {
		
		return servicioCobrarTickets.calcularPrecioPorHora(precio, desde, hasta);
	}

	@Override
	public void registrarTicket(Ticket ticket) {
		
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
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
