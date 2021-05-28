<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Ticket;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobrarTickets;

@Controller
public class ControladorPagarGarage {
	
	private ServicioCobrarTickets servicioCobrarTickets;
	
	@Autowired
	public ControladorPagarGarage(ServicioCobrarTickets servicioCobrarTickets) {
		this.servicioCobrarTickets = servicioCobrarTickets; 
	}
	
	@RequestMapping("mostrarFormularioReservaEstadia/{id}")
	public ModelAndView mostrarFormularioReservaEstadia(@PathVariable("id") Long id) {
		
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		
		List<Garage> listaGarage = servicioCobrarTickets.consultarGarage();
		
		for(Garage garage : listaGarage) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				modelo.put("ticket", ticket);
			}
		}
		
		return new ModelAndView("formularioReservaEstadia", modelo);
	}
	@RequestMapping(path="/realizarReservaEstadia/{id}")
	public ModelAndView procesarPagoEstadia(@RequestParam(value="fechaDesde")String fechaDesde,
									@RequestParam(value="fechaHasta")String fechaHasta,
									@PathVariable("id") Long id){
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		List<Garage> garageBuscado = servicioCobrarTickets.consultarGarage();
		for(Garage garage : garageBuscado) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				ticket.setFechaDesde(fechaDesde);
				ticket.setFechaHasta(fechaHasta);
				ticket.setGarage1(garage);
				
				Long dias = servicioCobrarTickets.calcularDias(ticket.getFechaDesde(), ticket.getFechaHasta());
				
				Double precio = servicioCobrarTickets.calcularPrecioPorEstadia(garage.getPrecioEstadia(), fechaDesde, fechaHasta);
				
				ticket.setPrecioAPagar(precio);
				
				modelo.put("ticket", ticket);
				
				modelo.put("precio", precio);
				
				modelo.put("dias", dias);
				
				servicioCobrarTickets.registrarTicket(ticket);
			}
		}
		
		return new ModelAndView("pagarMontoEstadia", modelo);
	}
	
	@RequestMapping("mostrarFormularioReservaHora/{id}")
	public ModelAndView mostrarFormularioReservaHora(@PathVariable("id") Long id) {
		
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		
		List<Garage> listaGarage = servicioCobrarTickets.consultarGarage();
		
		for(Garage garage : listaGarage) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				modelo.put("ticket", ticket);
			}
		}
		
		return new ModelAndView("formularioReservaHora", modelo);
	}
	
	@RequestMapping(path="/realizarReservaHora/{id}")
	public ModelAndView procesarPagoHora(@RequestParam(value="horaDesde")String horaDesde,
									@RequestParam(value="horaHasta")String horaHasta,
									@PathVariable("id") Long id){
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		List<Garage> garageBuscado = servicioCobrarTickets.consultarGarage();
		for(Garage garage : garageBuscado) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				ticket.setHoraDesde(horaDesde);
				ticket.setHoraHasta(horaHasta);
				ticket.setGarage1(garage);
				
				Long horas = servicioCobrarTickets.calcularHoras(ticket.getHoraDesde(), ticket.getHoraHasta());
				
				Double precio = servicioCobrarTickets.calcularPrecioPorHora(garage.getPrecioHora(), horaDesde, horaHasta);
				
				ticket.setPrecioAPagar(precio);
				
				modelo.put("ticket", ticket);
				
				modelo.put("precio", precio);
				
				modelo.put("horas", horas);
				
				servicioCobrarTickets.registrarTicket(ticket);
			}
		}
		
		return new ModelAndView("pagarMontoHora", modelo);
	}
}
=======
package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Ticket;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobrarTickets;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;

@Controller
public class ControladorPagarGarage {
	
	private ServicioCobrarTickets servicioCobrarTickets;
	
	private ServicioGarage servicioGarage;
	
	private ServicioBilletera servicioBilletera;
	
	private ServicioCliente servicioCliente;
	
	@Autowired
	public ControladorPagarGarage(ServicioCobrarTickets servicioCobrarTickets, ServicioBilletera servicioBilletera, ServicioGarage servicioGarage, ServicioCliente servicioCliente) {
		this.servicioCobrarTickets = servicioCobrarTickets;
		this.servicioBilletera = servicioBilletera;
		this.servicioGarage = servicioGarage;
		this.servicioCliente = servicioCliente;
	}
	
	//RESERVA POR ESTADIA
	
	@RequestMapping("mostrarFormularioReservaEstadia/{id}")
	public ModelAndView mostrarFormularioReservaEstadia(@PathVariable("id") Long id) {
		
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		
		List<Garage> listaGarage = servicioCobrarTickets.consultarGarage();
		
		for(Garage garage : listaGarage) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				modelo.put("ticket", ticket);
			}
		}
		
		return new ModelAndView("formularioReservaEstadia", modelo);
	}
	@RequestMapping(path="/realizarReservaEstadia/{id}")
	public ModelAndView procesarReservaEstadia(@RequestParam(value="fechaDesde")String fechaDesde,
									@RequestParam(value="fechaHasta")String fechaHasta,
									@PathVariable("id") Long id){
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		List<Garage> garageBuscado = servicioCobrarTickets.consultarGarage();
		for(Garage garage : garageBuscado) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				ticket.setFechaDesde(fechaDesde);
				ticket.setFechaHasta(fechaHasta);
				ticket.setGarage(garage);
				
				Long dias = servicioCobrarTickets.calcularDias(ticket.getFechaDesde(), ticket.getFechaHasta());
				
				Double precio = servicioCobrarTickets.calcularPrecioPorEstadia(garage.getPrecioEstadia(), fechaDesde, fechaHasta);
				
				ticket.setPrecioAPagar(precio);
				
				modelo.put("ticket", ticket);
				
				modelo.put("precio", precio);
				
				modelo.put("dias", dias);
				
				servicioCobrarTickets.registrarTicket(ticket);
			}
		}
		
		return new ModelAndView("pagarMontoEstadia", modelo);
	}
	
	@RequestMapping("/pagarEstadia/{id}")
	public ModelAndView pagarEstadia(@PathVariable("id") Long id,
									@RequestParam("cliente")Integer dni) {
		ModelMap modelo = new ModelMap();
		Garage garageBuscado = servicioGarage.buscarGaragePorId(id);
		Cliente clienteBuscado = servicioCliente.buscarClientePorDni(dni);
		Ticket ticketBuscado = servicioCobrarTickets.buscarTicketPertenencienteAlGarage(id);
		if(garageBuscado != null && clienteBuscado != null) {
			Billetera billetera = clienteBuscado.getBilletera();
			Double saldoBilletera = billetera.getSaldo();
			if(ticketBuscado.getPrecioAPagar() < saldoBilletera) {
				servicioBilletera.pagarReserva(ticketBuscado.getPrecioAPagar(), billetera.getSaldo());
				modelo.put("garage", garageBuscado);
				modelo.put("cliente", clienteBuscado);
				modelo.put("ticket", ticketBuscado);
				return new ModelAndView("confirmacionReserva", modelo);
			}
			
		}
		
		return new ModelAndView("redirect:/home");
	}
	
	//RESERVA POR HORA
	
	@RequestMapping("mostrarFormularioReservaHora/{id}")
	public ModelAndView mostrarFormularioReservaHora(@PathVariable("id") Long id) {
		
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		
		List<Garage> listaGarage = servicioCobrarTickets.consultarGarage();
		
		for(Garage garage : listaGarage) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				modelo.put("ticket", ticket);
			}
		}
		
		return new ModelAndView("formularioReservaHora", modelo);
	}
	
	@RequestMapping(path="/realizarReservaHora/{id}")
	public ModelAndView procesarReservaHora(@RequestParam(value="horaDesde")String horaDesde,
									@RequestParam(value="horaHasta")String horaHasta,
									@PathVariable("id") Long id){
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		List<Garage> garageBuscado = servicioCobrarTickets.consultarGarage();
		for(Garage garage : garageBuscado) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				ticket.setHoraDesde(horaDesde);
				ticket.setHoraHasta(horaHasta);
				ticket.setGarage(garage);
				
				Long horas = servicioCobrarTickets.calcularHoras(ticket.getHoraDesde(), ticket.getHoraHasta());
				
				Double precio = servicioCobrarTickets.calcularPrecioPorHora(garage.getPrecioHora(), horaDesde, horaHasta);
				
				ticket.setPrecioAPagar(precio);
				
				modelo.put("ticket", ticket);
				
				modelo.put("precio", precio);
				
				modelo.put("horas", horas);
				
				servicioCobrarTickets.registrarTicket(ticket);
			}
		}
		
		return new ModelAndView("pagarMontoHora", modelo);
	}
}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Ticket;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobrarTickets;

@Controller
public class ControladorPagarGarage {
	
	private ServicioCobrarTickets servicioCobrarTickets;
	
	@Autowired
	public ControladorPagarGarage(ServicioCobrarTickets servicioCobrarTickets) {
		this.servicioCobrarTickets = servicioCobrarTickets; 
	}
	
	@RequestMapping("mostrarFormularioReservaEstadia/{id}")
	public ModelAndView mostrarFormularioReservaEstadia(@PathVariable("id") Long id) {
		
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		
		List<Garage> listaGarage = servicioCobrarTickets.consultarGarage();
		
		for(Garage garage : listaGarage) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				modelo.put("ticket", ticket);
			}
		}
		
		return new ModelAndView("formularioReservaEstadia", modelo);
	}
	@RequestMapping(path="/realizarReservaEstadia/{id}")
	public ModelAndView procesarPagoEstadia(@RequestParam(value="fechaDesde")String fechaDesde,
									@RequestParam(value="fechaHasta")String fechaHasta,
									@PathVariable("id") Long id){
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		List<Garage> garageBuscado = servicioCobrarTickets.consultarGarage();
		for(Garage garage : garageBuscado) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				ticket.setFechaDesde(fechaDesde);
				ticket.setFechaHasta(fechaHasta);
				ticket.setGarage1(garage);
				
				Long dias = servicioCobrarTickets.calcularDias(ticket.getFechaDesde(), ticket.getFechaHasta());
				
				Double precio = servicioCobrarTickets.calcularPrecioPorEstadia(garage.getPrecioEstadia(), fechaDesde, fechaHasta);
				
				ticket.setPrecioAPagar(precio);
				
				modelo.put("ticket", ticket);
				
				modelo.put("precio", precio);
				
				modelo.put("dias", dias);
				
				servicioCobrarTickets.registrarTicket(ticket);
			}
		}
		
		return new ModelAndView("pagarMontoEstadia", modelo);
	}
	
	@RequestMapping("mostrarFormularioReservaHora/{id}")
	public ModelAndView mostrarFormularioReservaHora(@PathVariable("id") Long id) {
		
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		
		List<Garage> listaGarage = servicioCobrarTickets.consultarGarage();
		
		for(Garage garage : listaGarage) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				modelo.put("ticket", ticket);
			}
		}
		
		return new ModelAndView("formularioReservaHora", modelo);
	}
	
	@RequestMapping(path="/realizarReservaHora/{id}")
	public ModelAndView procesarPagoHora(@RequestParam(value="horaDesde")String horaDesde,
									@RequestParam(value="horaHasta")String horaHasta,
									@PathVariable("id") Long id){
		ModelMap modelo = new ModelMap();
		Ticket ticket = new Ticket();
		List<Garage> garageBuscado = servicioCobrarTickets.consultarGarage();
		for(Garage garage : garageBuscado) {
			if(garage.getId().equals(id)) {
				modelo.addAttribute("garage", servicioCobrarTickets.contultarUnGarage(garage));
				ticket.setHoraDesde(horaDesde);
				ticket.setHoraHasta(horaHasta);
				ticket.setGarage1(garage);
				
				Long horas = servicioCobrarTickets.calcularHoras(ticket.getHoraDesde(), ticket.getHoraHasta());
				
				Double precio = servicioCobrarTickets.calcularPrecioPorHora(garage.getPrecioHora(), horaDesde, horaHasta);
				
				ticket.setPrecioAPagar(precio);
				
				modelo.put("ticket", ticket);
				
				modelo.put("precio", precio);
				
				modelo.put("horas", horas);
				
				servicioCobrarTickets.registrarTicket(ticket);
			}
		}
		
		return new ModelAndView("pagarMontoHora", modelo);
	}
}
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
