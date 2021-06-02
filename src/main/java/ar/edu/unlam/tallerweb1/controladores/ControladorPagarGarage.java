
package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobrarTickets;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;

@Controller
public class ControladorPagarGarage {
	private ServicioEstacionamiento servicioEst;
	private ServicioCobrarTickets servicioCobrarTickets;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	private ServicioGarage servicioGarage;
	
	@Autowired
	public ControladorPagarGarage(ServicioCobrarTickets servicioCobrarTickets,ServicioAuto servicioAuto,ServicioCliente servicioCliente,ServicioEstacionamiento servicioEst,ServicioGarage servicioGarage) {
		this.servicioCobrarTickets = servicioCobrarTickets;
		this.servicioAuto = servicioAuto;
		this.servicioCliente = servicioCliente;
		this.servicioEst = servicioEst;
		this.servicioGarage= servicioGarage;
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
	
	@RequestMapping(path="/mostrarFormularioReservaHora/{cliente.id}/{auto.id}/{garage.id}", method=RequestMethod.GET)
	public ModelAndView mostrarFormularioReservaHora(
			@PathVariable("cliente.id") Long idCliente,
			@PathVariable("auto.id") Long idAuto,
			@PathVariable("garage.id") Long idGarage) {
		
		ModelMap modelo = new ModelMap();
		Estacionamiento ticket = new Estacionamiento();
		Auto auto1 = servicioAuto.buscarAuto(idAuto);
		Cliente cliente1 = servicioCliente.consultarClientePorId(idCliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		
			if(auto1 !=null && cliente1 !=null && garage !=null) {
				
				modelo.put("auto", auto1);
				modelo.put("cliente", cliente1);
				modelo.put("ticket", ticket);
				modelo.put("garage", garage);
				
				return new ModelAndView("formularioReservaHora", modelo);
			}
		
		
		return new ModelAndView("formularioReservaHora", modelo);
	}
	
	@RequestMapping(path="/realizarReservaHora/{id}/{auto}/{cliente}")
	public ModelAndView procesarPagoHora(@RequestParam(value="horaDesde")String horaDesde,
									@RequestParam(value="horaHasta")String horaHasta,
									@PathVariable("id") Long idGarage,
									@PathVariable("auto") Long idAuto,
									@PathVariable("cliente") Long idCliente){
		ModelMap modelo = new ModelMap();
		Estacionamiento est = new Estacionamiento();
		
		Auto auto = servicioAuto.buscarAuto(idCliente);
		Cliente cliente = servicioCliente.consultarClientePorId(idCliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		                                       //Esto le puse Nuevo
			if(garage !=null && auto!=null && auto.getUsandoGarage().equals(false) && garage.getCapacidad()>garage.getContador()) {
				modelo.put("auto", auto);
				modelo.put("cliente", cliente);
				modelo.put("garage", garage);
				est.setHoraDesde(horaDesde);
				est.setHoraHasta(horaHasta);
				est.setGarage1(garage);
				
				
				auto.setUsandoGarage(true);
				
				garage.setContador(garage.getContador()+1);
				
				est.setAuto(auto);
				est.setGarage1(garage);
				
				Long horas = servicioCobrarTickets.calcularHoras(est.getHoraDesde(), est.getHoraHasta());
				Double precio = servicioCobrarTickets.calcularPrecioPorHora(garage.getPrecioHora(), horaDesde, horaHasta);
				
				est.setPrecioAPagar(precio);
				
				modelo.put("ticket", est);
				
				modelo.put("precio", precio);
				
				modelo.put("horas", horas);
				
				servicioCobrarTickets.registrarTicket(est);
				return new ModelAndView("pagarMontoHora", modelo);
			}
		
		
		return new ModelAndView("pagarMontoHora", modelo);
	}
	
}
