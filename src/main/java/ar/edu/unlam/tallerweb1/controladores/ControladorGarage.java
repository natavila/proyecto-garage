package ar.edu.unlam.tallerweb1.controladores;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller	
public class ControladorGarage {
	
	private ServicioGarage servicioGarage;
	private ServicioRegistro servicioRegistro;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	private ServicioLogin servicioLogin;
	private ServicioEstacionamiento servicioEst;
	@Autowired
	public ControladorGarage(ServicioGarage servicioGarage, ServicioRegistro servicioRegistro,ServicioAuto servicioAuto,ServicioCliente servicioCliente,ServicioLogin servicioLogin,ServicioEstacionamiento servicioEst ){
		this.servicioGarage = servicioGarage;
		this.servicioRegistro = servicioRegistro;
		this.servicioAuto =servicioAuto;
		this.servicioCliente = servicioCliente;
		this.servicioLogin = servicioLogin;
		this.servicioEst = servicioEst;
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

	
	@RequestMapping(path="/mostrarAutosDeUnGarage/{id}", method=RequestMethod.GET)
	public ModelAndView MostrarAutosDeGarage( @PathVariable("id")Long id,
			HttpServletRequest request){
		
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
		    ModelMap modelo = new ModelMap();
			Garage garage2 = servicioGarage.buscarGarage(id);
			HashSet<Auto> autos = servicioEst.buscarAutosQueEstenActivosEnUnGarage(garage2);
			modelo.put("garage", garage2);
			modelo.put("auto", autos);
			
			return new ModelAndView("ListaAutosEnGarage", modelo);
			}
		return new ModelAndView("redirect:/login");
	}
	
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
				modelo.put("auto",servicioAuto.consultarAutoDeCliente(cliente) );
				modelo.addAttribute("autos", servicioAuto.consultarAutoDeCliente(cliente));
				return new ModelAndView ("ListaAutosDeCliente", modelo);
			}
		return new ModelAndView("redirect:/login");
		
	}
	
	@RequestMapping( path="/ElegirGaragesEst/{clienteId}/{autoId}", method=RequestMethod.GET)
	public ModelAndView reservarAutoGarage(@PathVariable("clienteId")Long clienteId,
			@PathVariable("autoId")Long autoId,
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
		modelo.put("garages", servicioGarage.consultarGarage());
		
		return new ModelAndView ("listaGarages", modelo);
		}
		return new ModelAndView("redirect:/login");
	}
	
}
