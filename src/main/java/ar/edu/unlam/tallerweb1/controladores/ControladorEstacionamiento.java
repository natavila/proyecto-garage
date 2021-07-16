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
import ar.edu.unlam.tallerweb1.modelo.Cliente;
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
	private ServicioCliente servicioCliente;
	
	@Autowired
	public ControladorEstacionamiento(ServicioCliente servicioCliente, ServicioGarage servicioGarage,ServicioAuto servicioAuto,ServicioEstacionamiento servicioEst){
		this.servicioGarage= servicioGarage;
		this.servicioAuto = servicioAuto;
		this.servicioEst = servicioEst;
		this.servicioCliente = servicioCliente;
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