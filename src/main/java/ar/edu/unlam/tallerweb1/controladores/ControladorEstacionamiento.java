package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
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
	private ServicioEstacionamiento servicioEst;
	
	@Autowired
	public ControladorEstacionamiento(ServicioGarage servicioGarage,ServicioAuto servicioAuto,ServicioEstacionamiento servicioEst){
		this.servicioGarage= servicioGarage;
		this.servicioAuto = servicioAuto;
		this.servicioEst = servicioEst;
	}

	
	@RequestMapping(path = "/sacarAuto/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
	public ModelAndView sacarAuto(@PathVariable("id")Long id) {
		ModelMap modelo = new ModelMap();
		modelo.put("id", id);
		return new ModelAndView("SacarAutoPorTicket", modelo);
	}
	
	@RequestMapping(path="/sacarAutoDeGaragess/{id}", method=RequestMethod.GET)
	public ModelAndView SacarAutoDeGaragePorTicket( @RequestParam(value="retirarAuto")Long retirarAuto,
			@PathVariable("id")Long id,
			HttpServletRequest request
			){
		ModelMap model = new ModelMap();
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
				
			
				Garage garage2 = servicioGarage.buscarGarage(id);
				ArrayList<Auto> autos = (ArrayList<Auto>) servicioEst.buscarAutosQueEstenActivosEnUnGarage(garage2);
				Estacionamiento est= servicioEst.buscarEstacionamiento(retirarAuto);
				for(Auto e: autos) {
					if(e.getId().equals(est.getAuto().getId())) {
						servicioAuto.cambiarEstadoDeSiestaEnGarageOno(e);
						//saco la reserva de Auto
						servicioAuto.cambiarEstadoReservaAuto(e);
					}
				}
				if(est !=null && garage2 != null) {
					servicioGarage.restarContador(garage2);
					servicioEst.cambiarEstadoEstacionamiento(est);
					servicioEst.cambiarEstadoDeReserva(est);
					model.put("error", "Baja correcta");
				}else {
					model.put("error", "El Auto No esta");
				}
				
			}
		return new ModelAndView("confirmacionSacarTicket", model);
	}


	public ServicioAuto getServicioAuto() {
		return servicioAuto;
	}


	public void setServicioAuto(ServicioAuto servicioAuto) {
		this.servicioAuto = servicioAuto;
	}


	public ServicioGarage getServicioGarage() {
		return servicioGarage;
	}


	public void setServicioGarage(ServicioGarage servicioGarage) {
		this.servicioGarage = servicioGarage;
	}


	public ServicioEstacionamiento getServicioEst() {
		return servicioEst;
	}


	public void setServicioEst(ServicioEstacionamiento servicioEst) {
		this.servicioEst = servicioEst;
	}
	
	
	
	
}