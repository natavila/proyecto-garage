package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.util.Map;

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
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
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
	@Autowired
	public ControladorGarage(ServicioGarage servicioGarage, ServicioRegistro servicioRegistro,ServicioAuto servicioAuto,ServicioCliente servicioCliente,ServicioLogin servicioLogin ){
		this.servicioGarage = servicioGarage;
		this.servicioRegistro = servicioRegistro;
		this.servicioAuto =servicioAuto;
		this.servicioCliente = servicioCliente;
		this.servicioLogin = servicioLogin;
	}
	
	@RequestMapping("/homeGarages")
	public ModelAndView homeGarages() {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("HomeGarages", modelo);
	}
	
	@RequestMapping("/formularioAgregarGarage")
	public ModelAndView mostrarFormularioGaraga() {
		ModelMap modelo = new ModelMap();
		Garage garage1 = new Garage();
		modelo.put("garage", garage1);
		return new ModelAndView("agregarGarage", modelo);
	}
	
	
	
	@RequestMapping(path="confirmarAgregarGarage", method = RequestMethod.POST)
	public String agregarGarage(
	@ModelAttribute ("garage") Garage garage1){
	servicioGarage.agregarGarage(garage1);
	return "redirect:/lista";
		
	}
	
	@RequestMapping("/lista")
	public ModelAndView Listar(){
		ModelMap modelo = new ModelMap();
		//Garage garage = new Garage();
		modelo.addAttribute("garages", servicioGarage.consultarGarage());
		return new ModelAndView("DatosGaragesPorPantalla", modelo);
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
	@RequestMapping(path="/agregarAutoAGarage/{id}", method=RequestMethod.POST)
	public ModelAndView agregarAutoAGarage( @PathVariable("id")Long id,
			@RequestParam(value="patente", required=false)String patente,
			@ModelAttribute("auto") Auto auto) {
		
		ModelMap modelo = new ModelMap();
		List<Garage> listaGarage = servicioGarage.consultarGarage();
		List<Auto> listaAuto = servicioAuto.listaDeAutos();
	for(Garage g1: listaGarage ) {
		if(g1.getId().equals(id)) {
			for(Auto autos: listaAuto) {
				if(autos.getPatente().equals(patente)) {
					modelo.addAttribute("garage",servicioGarage.asignarAutoaGarage(g1, auto));
					return new ModelAndView("redirect:/lista");
				}
			}
		}		
	}		
		
	return new ModelAndView("DatosDeUnGaragePorPantalla", modelo);
	}
	
		*/
	/*
	@RequestMapping(path="/mostrarAutosDeUnGarage/{id}", method=RequestMethod.GET)
	public ModelAndView MostrarAutosDeGarage( @PathVariable("id")Long id){
		List<Garage> listaGarage = servicioGarage.consultarGarage();
		ModelMap modelo = new ModelMap();
			for(Garage g1: listaGarage) {
				if(g1.getId().equals(id)) {
					String nombre= g1.getNombre();
					List<Auto> autosEnGarage = servicioGarage.consultarAutosEnGarage(g1);
					modelo.addAttribute("autos", autosEnGarage);
					modelo.addAttribute("nombre", nombre);
				}
			}
			return new ModelAndView("DatosAutosEnGarage", modelo);
	
	}
	*/
	
	@RequestMapping( path="/mostrarGarages/{id}/{nombre}", method=RequestMethod.GET)
	public ModelAndView garagesParaReservar(@PathVariable("id")Long id,
			@PathVariable("nombre")String nombre
			){
		ModelMap modelo = new ModelMap();
		modelo.addAttribute(nombre);
		modelo.addAttribute(id);
		Cliente cliente = servicioLogin.consultarClientePorId(id);
		modelo.put("cliente", cliente);
		modelo.put("auto",servicioAuto.consultarAutoDeCliente(cliente) );
		modelo.addAttribute("autos", servicioAuto.consultarAutoDeCliente(cliente));
		return new ModelAndView ("ListaAutosDeCliente", modelo);
	}
	
	@RequestMapping( path="/ElegirGaragesEst/{clienteId}/{autoId}", method=RequestMethod.GET)
	public ModelAndView reservarAutoGarage(@PathVariable("clienteId")Long clienteId,
			@PathVariable("autoId")Long autoId
			){
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioLogin.consultarClientePorId(clienteId);
		Auto auto = servicioAuto.buscarAuto(autoId);
		
		modelo.put("cliente", cliente);
		modelo.put("auto", auto);
		modelo.addAttribute("garages", servicioGarage.consultarGarage());
		return new ModelAndView ("listaGarages", modelo);
	}
	
	
	
	
	/*@RequestMapping("/formularioAgregarGarage")
	public ModelAndView mostrarFormularioGaraga() {
		ModelMap modelo = new ModelMap();
		Garage garage1 = new Garage();
		modelo.put("garage", garage1);
		return new ModelAndView("agregarGarage", modelo);
	}
	
	
	
	@RequestMapping(path="confirmarAgregarGarage", method = RequestMethod.POST)
	public String agregarGarage(
	@ModelAttribute ("garage") Garage garage1){
	servicioGarage.agregarGarage(garage1);
	return "redirect:/lista";
		
	}
	
	@RequestMapping("/buscarGarage")
	public ModelAndView mostrarBuscarGaraga() {
		ModelMap modelo = new ModelMap();
		Garage garage= new Garage();
		modelo.put("garage", garage);
		return new ModelAndView("GaragesBD", modelo);
	}
	
	@RequestMapping("/lista")
	public String Lista(Model modelo){
		modelo.addAttribute("garages", servicioGarage.consultarGarage());
		return ("DatosGaragesPorPantalla");
	}
	
	
	
	@RequestMapping("/mostrarGarages")
	public String garagesParaReservar(Model modelo){
		modelo.addAttribute("garages", servicioGarage.consultarGarage());
		return ("listaGarages");
	}
	
	@RequestMapping("/lista/eliminar/{id}")
	public String eliminarGarage(@PathVariable("id")Long id, Model model) {
		
		servicioGarage.eliminarGarage(id);
		return "redirect:/lista";
	}*/
	
	

}
