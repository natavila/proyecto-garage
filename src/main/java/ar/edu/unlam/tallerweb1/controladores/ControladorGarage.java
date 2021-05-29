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
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller	
public class ControladorGarage {
	
	private ServicioGarage servicioGarage;
	private ServicioRegistro servicioRegistro;

	@Autowired
	public ControladorGarage(ServicioGarage servicioGarage, ServicioRegistro servicioRegistro){
		this.servicioGarage = servicioGarage;
		this.servicioRegistro = servicioRegistro;
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
	
	@RequestMapping(path="/agregarAutoAGarage/{id}", method=RequestMethod.POST)
	public ModelAndView agregarAutoAGarage( @PathVariable("id")Long id,
			@RequestParam(value="patente", required=false)String patente,
			@ModelAttribute("auto") Auto auto) {
		
		ModelMap modelo = new ModelMap();
		List<Garage> listaGarage = servicioGarage.consultarGarage();
		List<Auto> listaAuto = servicioRegistro.listaAuto();
		
	for(Garage g1: listaGarage ) {
		if(g1.getId().equals(id)) {
			for(Auto autos: listaAuto) {
				if(autos.getPatente().equals(patente)) {
					//modelo.addAttribute("garage",servicioGarage.asignarAutoaGarage(g1, auto));
					return new ModelAndView("redirect:/lista");
				}
			}
		}		
	}		
		
	return new ModelAndView("DatosDeUnGaragePorPantalla", modelo);
	}
	
		
	
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
	
	@RequestMapping("/mostrarGarages/{id}/{nombre}")
	public ModelAndView garagesParaReservar(Model modelo,@PathVariable("id")Long id,
			@PathVariable("nombre")String nombre){
		ModelMap modelo1 = new ModelMap();
		modelo1.addAttribute(nombre);
		modelo1.addAttribute(id);
		modelo1.addAttribute("garages", servicioGarage.consultarGarage());
		return new ModelAndView ("listaGarages", modelo1);
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
