package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller
public class ControladorRegistroAuto {
	
	private ServicioCliente servicioCliente;
	private ServicioPlan servicioPlan;
	private ServicioAuto servicioAuto;
	
	
	@Autowired
	public ControladorRegistroAuto(ServicioCliente servicioCliente, ServicioAuto servicioAuto, ServicioPlan servicioPlan){

		this.servicioCliente = servicioCliente;
		this.servicioAuto = servicioAuto;
		this.servicioPlan = servicioPlan;
	}
	
	
	@RequestMapping("/mostrarRegistroAuto")
	public 	ModelAndView registro(HttpServletRequest request) {
		
		String rol = (String) request.getSession().getAttribute("roll");
		Long id = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		ModelMap modelo = new ModelMap();
		Auto auto = new Auto(); 
		
		if(cliente != null && rol != null)
			if(rol.equals("cliente")) {

			modelo.put("cliente", cliente);
			auto.setCliente(cliente);
			modelo.put("auto", auto);
			
			return new ModelAndView("registroAuto", modelo);
		}
			
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path="/procesarRegistroAuto", method=RequestMethod.POST)
	public ModelAndView procesarRegistroAuto(
			@ModelAttribute("auto") Auto auto, HttpServletRequest request){
		
		String rol = (String) request.getSession().getAttribute("roll");
		Long id = (Long) request.getSession().getAttribute("id");
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Auto autoRegistrado= servicioAuto.consultarAuto(auto);
		
		if(cliente != null && rol.equals("cliente")) {
		 if(auto.getPatente() != "") {
			 if(autoRegistrado == null) {
				 	if(cliente.getPlan() == null) {
					 	modelo.put("cliente", cliente);
						auto.setCliente(cliente);
						modelo.put("auto", auto);
						auto.setEnUso(true);
						auto.setUsandoGarage(false);
						auto.setReservado(false);
						servicioAuto.registrarAuto(auto);

						modelo.put("mensaje", "Auto Registrado correctamente");
				 		
						return new ModelAndView("redirect:/misAutos");
						
				 	}else if(cliente.getCantidadAutosRestantes() <= cliente.getPlan().getCantidadAutosPermitidos()){
				 		modelo.put("cliente", cliente);
						auto.setCliente(cliente);
						modelo.put("auto", auto);
						auto.setEnUso(true);
						auto.setUsandoGarage(false);
						auto.setReservado(false);
						servicioAuto.registrarAuto(auto);
				 		servicioPlan.actualizarCantidadDeAutosPlan(cliente);
				 		modelo.put("mensaje", "Auto Registrado correctamente");
				 		return new ModelAndView("redirect:/misAutos");
				 		
						}else {
							modelo.put("cliente", cliente);
					 		modelo.put("auto", auto);
							modelo.put("mensaje", "Usted alcanzo el limite de autos permitidos.");
							return new ModelAndView("registroAuto", modelo);
						}
				 	
				 	}else {
			 		modelo.put("cliente", cliente);
			 		modelo.put("auto", auto);
			 		modelo.put("mensaje", "Patente ya registrada. Ingrese otra patente.");
			 		return new ModelAndView("registroAuto", modelo);
			 	}
			 	// Lo que hace es: poder cambiar de dueño del auto o sacarlo y ponerlo, sin q tenga duplicados o que no se pueda agregar
		 	}else {
		 		
		 		modelo.put("cliente", cliente);
		 		modelo.put("auto", auto);
		 		modelo.put("mensaje", "Ingrese una patente valida.");
		 		return new ModelAndView("registroAuto", modelo);
		 	}
		 
		}else if(autoRegistrado != null && autoRegistrado.getEnUso().equals(false)) {
		 		modelo.addAttribute("cliente", cliente);
		 
		 		autoRegistrado.setCliente(cliente);

				servicioAuto.cambiarEstadoDeUso(autoRegistrado);
				modelo.put("auto", autoRegistrado);
				
				modelo.put("mensaje", "Auto Reingresado Correctamene");
		 		
				return new ModelAndView("confirmacionRegistroAuto", modelo);
		 	}
		
		return new ModelAndView("redirect:/login");
		
		}


	public void setServicioCliente(ServicioCliente servicioClienteMock) {
		this.servicioCliente = servicioClienteMock;
		
	}


	public void setServicioAuto(ServicioAuto servicioAutoMock) {
		this.servicioAuto = servicioAutoMock;
		
	}
	
}
