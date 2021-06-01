package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller
public class ControladorEstacionamiento {
	private ServicioAuto servicioAuto;
	private ServicioGarage servicioGarage;
	private ServicioEstacionamiento servicioEstacionamiento;
	
	@Autowired
	public ControladorEstacionamiento(ServicioGarage servicioGarage,ServicioAuto servicioAuto,ServicioEstacionamiento servicioEstacionamiento){
		this.servicioGarage= servicioGarage;
		this.servicioAuto = servicioAuto;
		this.servicioEstacionamiento = servicioEstacionamiento;
	}
	/*
	@RequestMapping(path="/Asignacion", method=RequestMethod.GET)
	public ModelAndView asignacion(@PathVariable("auto") Long idAuto, 
			@PathVariable("garage") Long IdGarage) {
			ModelMap modelo = new ModelMap(); 
			Garage garage = servicioGarage.buscarGarage(IdGarage);
			Auto auto = servicioAuto.buscarAuto(idAuto);
			if(garage!=null && auto !=null) {
				servicioEstacionamiento.asignarAutoaGarage(garage, auto);
				modelo.put("mensaje", "Asgnacion Correcta");
			}else {modelo.put("mensaje", "NOOOOOO!!!!");
				
			}
			return new ModelAndView("confirmacionRegistroAuto", modelo);
			
	}
	*/
	
}