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
	
	@RequestMapping("/formularioAgregarGarage")
	public ModelAndView mostrarFormularioGaraga(HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
				ModelMap modelo = new ModelMap();
				Garage garage1 = new Garage();
				modelo.put("garage", garage1);
				
				List <String> loc = servicioLoc.devolverNombresDeLocalidades();
				modelo.put("loc", loc);
				
				return new ModelAndView("agregarGarage", modelo);
			}
		return new ModelAndView("redirect:/login");	
		
	}
	
	
	
	@RequestMapping(path="confirmarAgregarGarage", method = RequestMethod.POST)
	public String agregarGarage(HttpServletRequest request,
	@ModelAttribute ("garage") Garage garage1){
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
	servicioGarage.agregarGarage(garage1);
	return "redirect:/lista";
			}
		return ("redirect:/login");
	}
	
	@RequestMapping("/lista")
	public ModelAndView Listar(HttpServletRequest request){
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("garages", servicioGarage.consultarGarage());
		return new ModelAndView("DatosGaragesPorPantalla", modelo);
			}
		return new ModelAndView("redirect:/login");
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
	
	
	
	
	@RequestMapping("/lista/eliminar/{id}")
	public ModelAndView eliminaGarge(@PathVariable("id")Long id) {
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("garage", servicioGarage.eliminarGarage(id));
		return new ModelAndView("redirect:/lista", modelo);
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

	/*
	@RequestMapping(path="/mostrarAutosDeUnGarage/{id}", method=RequestMethod.GET)
	public ModelAndView MostrarAutosDeGarage( @PathVariable("id")Long id,
			HttpServletRequest request){
		
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
				
		    ModelMap modelo = new ModelMap();
			Garage garage2 = servicioGarage.buscarGarage(id);
			HashSet<Auto> autos = (HashSet<Auto>) servicioEst.buscarAutosQueEstenActivosEnUnGarage(garage2);
			modelo.put("garage", garage2);
			modelo.put("auto", autos);
			
			return new ModelAndView("ListaAutosEnGarage", modelo);
			}
		return new ModelAndView("redirect:/login");
	}
	*/
	@RequestMapping(path="/mostrarHistoricoDeUnGarage/{id}", method=RequestMethod.GET)
	public ModelAndView MostrarHistoricoDeGarage( @PathVariable("id")Long id,
			HttpServletRequest request){
		
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
		    ModelMap modelo = new ModelMap();
			Garage garage2 = servicioGarage.buscarGarage(id);
			List<Auto> autos = servicioEst.buscarAutosDeUnGarage(garage2);
			modelo.put("garage", garage2);
			modelo.put("auto", autos);
			
			return new ModelAndView("ListaHistoricaDeAutosEnGarage", modelo);
			}
		return new ModelAndView("redirect:/login");
	}
	
	
	
	
	@RequestMapping( path="/mostrarGarages/{id}/{nombre}", method=RequestMethod.GET)
	public ModelAndView garagesParaReservar(@PathVariable("id")Long id,
			@PathVariable("nombre")String nombre, 
			HttpServletRequest request
			){
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
				ModelMap modelo = new ModelMap();
				modelo.addAttribute(nombre);
				modelo.addAttribute(id);
				Cliente cliente = servicioLogin.consultarClientePorId(id);
				modelo.put("cliente", cliente);
				
				modelo.put("auto",servicioAuto.consultarAutoDeClienteActivo(cliente));
				
				modelo.addAttribute("autos", servicioAuto.consultarAutoDeCliente(cliente));
				return new ModelAndView ("ListaAutosDeCliente", modelo);
			}
		return new ModelAndView("redirect:/login");
		
	}
	
	@RequestMapping( path="/ElegirGaragesEst/{clienteId}/{autoId}", method=RequestMethod.GET)
	public ModelAndView reservarAutoGarage(@PathVariable("clienteId")Long clienteId,
			@PathVariable("autoId")Long autoId,
			@RequestParam(value="palabraBuscada",required=true) String buscada,
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

		modelo.addAttribute("garages", servicioGarage.consultarGarage());

		modelo.put("garages", servicioGarage.buscarGaragePorLocalidad(buscada));
		

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
		modelo.put("garages", servicioGarage.buscarGaragePorLocalidad(Gbuscado));
		
		return new ModelAndView ("listaGarages", modelo);
		}
		return new ModelAndView("redirect:/login");
	}
	
	
	
	
	
	
	@RequestMapping(path="AdministrarGarage/{id}", method = RequestMethod.GET)
	public ModelAndView administrarGarage(HttpServletRequest request,
			@PathVariable("id") Long garage1){
		String rol = (String) request.getSession().getAttribute("roll");
		ModelMap modelo = new ModelMap();
		Garage garage = servicioGarage.buscarGarage(garage1);
		if(rol != null)
			if(rol.equals("admin")) {
				ArrayList<Auto> autos = (ArrayList<Auto>) servicioEst.buscarAutosQueEstenActivosEnUnGarage(garage);
				modelo.put("garage", garage);
				modelo.put("lugar", servicioGarage.cantidadDeLugarEnEst(garage));
				modelo.put("dinero", servicioEst.dineroGanadoEnElDia(garage));
				modelo.put("autos",autos);
				ArrayList<Long> tickets = servicioEst.numeroDeTicketAuto(garage);
				modelo.put("tickets",tickets );
				if(servicioGarage.cantidadDeLugarEnEst(garage)<=5 && servicioGarage.cantidadDeLugarEnEst(garage)>=1) {
					modelo.put("alerta", "Garage Con Pocos Lugares Disponibles");
				}else if(servicioGarage.cantidadDeLugarEnEst(garage)<=0) {
					modelo.put("Lleno", "Garage Sin Lugares");
					
				}else {
					modelo.put("ConLugar", "Con lugar");
				}
				
				
				return new ModelAndView ("AdministrarGarage", modelo);
			}
		return new ModelAndView("redirect:/login");
	}
	
	
	
	
	
	
}
