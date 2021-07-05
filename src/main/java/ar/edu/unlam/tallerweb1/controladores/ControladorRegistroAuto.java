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
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller
public class ControladorRegistroAuto {

	private ServicioRegistro servicioRegistro;
	
	private ServicioCliente servicioCliente;
	
	private ServicioAuto servicioAuto;
	
	
	@Autowired
	public ControladorRegistroAuto(ServicioRegistro servicioRegistro, ServicioCliente servicioCliente, ServicioAuto servicioAuto){
		
		this.servicioRegistro = servicioRegistro;
		this.servicioCliente = servicioCliente;
		this.servicioAuto = servicioAuto;
	}
	
	
	@RequestMapping("/mostrarRegistroAuto/{id}/{nombre}")
	public 	ModelAndView registro(@PathVariable("id") Long id, 
			@PathVariable("nombre") String nombre,
			HttpServletRequest request) {
		
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
		ModelMap modelo = new ModelMap(); //Agrupa todo para mandarlo a vista
		Auto auto = new Auto(); //Se crea un usuario vacio para mandarlo vacio para que el formulario se vaya llenando
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		
		if(cliente != null) {
			modelo.addAttribute("cliente", cliente);
			
			auto.setCliente(cliente);
			modelo.put("auto", auto);
			
			return new ModelAndView("registroAuto", modelo);
		}else {
			
			modelo.put("error", "Cliente no registrado");
		}
		
		 return new ModelAndView("registroAuto", modelo);
			}
		return new ModelAndView("redirect:/login");//Se le envia a la vista registro el modelo con el objeto usuario
	}
	
	@RequestMapping(path="/procesarRegistroAuto/{id}/{nombre}", method=RequestMethod.POST)
	public ModelAndView procesarRegistroAuto(
			@ModelAttribute("auto") Auto auto,
			@PathVariable("id") Long id,
			@PathVariable("nombre") String nombre){
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Auto auto1= servicioAuto.consultarAuto(auto);
		
		 if(auto.getPatente() != "" && cliente != null && auto1 == null) {
			 
				 	modelo.put("cliente", cliente);
					auto.setCliente(cliente);
					modelo.put("auto", auto);
					auto.setEnUso(true);
					auto.setUsandoGarage(false);
					auto.setReservado(false);
					servicioAuto.registrarAuto(auto);
					

					modelo.put("mensaje", "Auto Registrado correctamente");
			 		
					return new ModelAndView("confirmacionRegistroAuto", modelo);

					

			 
			 	// Lo que hace es: poder cambiar de dueño del auto o sacarlo y ponerlo, sin q tenga duplicados o que no se pueda agregar
		 	}else if(auto1 != null && auto1.getEnUso().equals(false)) {
		 		modelo.addAttribute("cliente", cliente);
		 
				auto1.setCliente(cliente);

				servicioAuto.cambiarEstadoDeUso(auto1);
				modelo.put("auto", auto1);
				
				modelo.put("mensaje", "Auto Reingresado Correctamene");
		 		
				return new ModelAndView("confirmacionRegistroAuto", modelo);
		 	}else {
		 		modelo.put("cliente", cliente);
		 		modelo.put("auto", auto);
		 		modelo.put("mensaje", "Patente ya registrada. Ingrese otra patente.");
		 		return new ModelAndView("registroAuto", modelo);
		 	}


		
		}
	
}
