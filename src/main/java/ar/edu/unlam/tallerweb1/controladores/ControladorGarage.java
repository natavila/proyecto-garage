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

	
	@RequestMapping(path="/mostrarAutosDeUnGarage/{id}", method=RequestMethod.GET)
	public ModelAndView MostrarAutosDeGarage( @PathVariable("id")Long id){
		
		ModelMap modelo = new ModelMap();
			Garage garage2 = servicioGarage.buscarGarage(id);
			List<Auto> autos = servicioEst.buscarAutosDeUnGarage(garage2);
		
			modelo.put("auto", autos);
			
			return new ModelAndView("ListaAutosEnGarage", modelo);
	
	}
	
	
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
