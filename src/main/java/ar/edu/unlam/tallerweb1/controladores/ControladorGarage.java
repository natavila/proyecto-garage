package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Buscador;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLocalidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller	
public class ControladorGarage {
	
	private ServicioGarage servicioGarage;
	private ServicioRegistro servicioRegistro;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	private ServicioLogin servicioLogin;
	private ServicioLocalidad servicioLoc;
	private ServicioEstacionamiento servicioEst;
	
	@Autowired
	public ControladorGarage(ServicioGarage servicioGarage, ServicioRegistro servicioRegistro,ServicioAuto servicioAuto,ServicioCliente servicioCliente,ServicioLocalidad servicioLoc,ServicioLogin servicioLogin,ServicioEstacionamiento servicioEst ){
		this.servicioGarage = servicioGarage;
		this.servicioRegistro = servicioRegistro;
		this.servicioAuto =servicioAuto;
		this.servicioCliente = servicioCliente;
		this.servicioLogin = servicioLogin;
		this.servicioEst = servicioEst;
		this.servicioLoc = servicioLoc;
	}
	
	@RequestMapping("/homeGarages")
	public ModelAndView homeGarages() {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("HomeGarages", modelo);
	}
	

	
	@RequestMapping("/listaPorHora")
	public ModelAndView ListarPorHora(HttpServletRequest request){
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
		ModelMap modelo = new ModelMap();
	
		modelo.addAttribute("garages", servicioGarage.ordenarGaragePorHora());
		return new ModelAndView("DatosGaragesPorPantalla", modelo);
			}
		return new ModelAndView("redirect:/login");
	
	}
	
	@RequestMapping("/listaPorEstadia")
	public ModelAndView ListarPorEstadia(HttpServletRequest request){
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
		ModelMap modelo = new ModelMap();
	
		modelo.addAttribute("garages", servicioGarage.ordenarGaragePorEstadia());
		return new ModelAndView("DatosGaragesPorPantalla", modelo);
			}
		return new ModelAndView("redirect:/login");
	
	}
	

	
	@RequestMapping ("/mostrarGarage/{id}")
	public ModelAndView mostrarGarage(@PathVariable ("id") Long id) {
		ModelMap modelo = new ModelMap();
		Auto auto = new Auto();
		List<Garage> listaGarage = servicioGarage.consultarGarage();
		for(Garage gara: listaGarage) {
			if(gara.getId().equals(id)) {
				modelo.addAttribute("garage", servicioGarage.contultarUnGarage(gara));
				modelo.put("auto", auto);
				return new ModelAndView("DatosDeUnGaragePorPantalla", modelo);
				
			}else {
				
			}
		}
		
		
		return new ModelAndView("DatosDeUnGaragePorPantalla", modelo);
	}

	
	
	
	
	
	
	@RequestMapping( path="/ElegirAuto", method=RequestMethod.GET)
	public ModelAndView autosParaReservar(HttpServletRequest request
			){
		
		ModelMap modelo = new ModelMap();
		Long id = (Long) request.getSession().getAttribute("id");
		String rol = (String) request.getSession().getAttribute("roll");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		List<Auto> autosDeCliente = servicioAuto.consultarAutoDeCliente(cliente);
		if(cliente != null)
			if(rol.equals("cliente")) {
				if(autosDeCliente != null) {
					List<Auto> autosSinGarage = servicioAuto.consultarAutosSinGarage();
					//Cliente cliente = servicioLogin.consultarClientePorId(id);				

					modelo.put("cliente", cliente);
					modelo.put("autosSinGarage",autosSinGarage);
					
					modelo.put("mensaje", "Sus autos ya se encuentran en un garage o tienen una reserva activa.");
					return new ModelAndView ("ListaAutosDeCliente", modelo);
				}else {
					
					return new ModelAndView ("redirect:/misAutos");
				}
				
			}
		return new ModelAndView("redirect:/login");
		
	}
	
	@RequestMapping( path="/ElegirGaragesEst/{autoId}", method=RequestMethod.GET)
	public ModelAndView reservarAutoGarage(@PathVariable("autoId")Long autoId,
			HttpServletRequest request
			){
		
		Long id = (Long) request.getSession().getAttribute("id");
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioLogin.consultarClientePorId(id);
		Auto auto = servicioAuto.buscarAuto(autoId);
		
		
		modelo.put("cliente", cliente);
		modelo.put("auto", auto);

		modelo.addAttribute("garages", servicioGarage.consultarGarage());
	

		return new ModelAndView ("listaGarages", modelo);
		}
		return new ModelAndView("redirect:/login");
	}
	
	
	@RequestMapping( path="/BuscarGaragesEst/{clienteId}/{autoId}", method=RequestMethod.GET)
	public ModelAndView BuscarGaragesEst(@PathVariable("clienteId")Long clienteId,
			@PathVariable("autoId")Long autoId,
			
			HttpServletRequest request
			){
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
		
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioLogin.consultarClientePorId(clienteId);
		Auto auto = servicioAuto.buscarAuto(autoId);
		
		List <String> loc = servicioLoc.devolverNombresDeLocalidades();
		modelo.put("loc", loc);
		
		modelo.put("cliente", cliente);
		modelo.put("auto", auto);
		
		
		return new ModelAndView ("buscarDestino", modelo);
		}
		return new ModelAndView("redirect:/login");
	}
	
	
	@RequestMapping( path="/ElegirGaragesEst/{clienteId}/{autoId}/", method=RequestMethod.GET)
	public ModelAndView ElegirGarageParaEst(@PathVariable("clienteId")Long clienteId,
			@PathVariable("autoId")Long autoId,
			@PathVariable("garageBuscado") String Gbuscado,
			HttpServletRequest request
			){
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioLogin.consultarClientePorId(clienteId);
		Auto auto = servicioAuto.buscarAuto(autoId);
		modelo.put("cliente", cliente);
		modelo.put("auto", auto);
		return new ModelAndView ("listaGarages", modelo);
		}
		return new ModelAndView("redirect:/login");
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	

	public ServicioGarage getServicioGarage() {
		return servicioGarage;
	}

	public void setServicioGarage(ServicioGarage servicioGarage) {
		this.servicioGarage = servicioGarage;
	}

	public ServicioRegistro getServicioRegistro() {
		return servicioRegistro;
	}

	public void setServicioRegistro(ServicioRegistro servicioRegistro) {
		this.servicioRegistro = servicioRegistro;
	}

	public ServicioAuto getServicioAuto() {
		return servicioAuto;
	}

	public void setServicioAuto(ServicioAuto servicioAuto) {
		this.servicioAuto = servicioAuto;
	}

	public ServicioCliente getServicioCliente() {
		return servicioCliente;
	}

	public void setServicioCliente(ServicioCliente servicioCliente) {
		this.servicioCliente = servicioCliente;
	}

	public ServicioLogin getServicioLogin() {
		return servicioLogin;
	}

	public void setServicioLogin(ServicioLogin servicioLogin) {
		this.servicioLogin = servicioLogin;
	}

	public ServicioLocalidad getServicioLoc() {
		return servicioLoc;
	}

	public void setServicioLoc(ServicioLocalidad servicioLoc) {
		this.servicioLoc = servicioLoc;
	}

	public ServicioEstacionamiento getServicioEst() {
		return servicioEst;
	}

	public void setServicioEst(ServicioEstacionamiento servicioEst) {
		this.servicioEst = servicioEst;
	}
	
	
	
	
}
