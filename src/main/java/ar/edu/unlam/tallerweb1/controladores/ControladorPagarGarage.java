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
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
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
		Estacionamiento ticket = new Estacionamiento();
		
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
		Estacionamiento ticket = new Estacionamiento();
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
		Estacionamiento ticket = new Estacionamiento();
		
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
		Estacionamiento ticket = new Estacionamiento();
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
