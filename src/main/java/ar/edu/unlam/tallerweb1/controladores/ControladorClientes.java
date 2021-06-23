package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.xdevapi.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller
public class ControladorClientes {
	
	private ServicioLogin servicioLogin;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	private ServicioBilletera servicioBilletera;
	private ServicioRegistro servicioRegistro;
	private ServicioEstacionamiento servicioEstacionamiento;
	
	@Autowired
	private ControladorClientes(ServicioLogin servicioLogin, ServicioRegistro servicioRegistro, ServicioAuto servicioAuto, ServicioCliente servicioCliente, ServicioBilletera servicioBilletera, ServicioEstacionamiento servicioEstacionamiento) {
		this.servicioAuto =servicioAuto;
		this.servicioLogin = servicioLogin;
		this.servicioCliente = servicioCliente;
		this.servicioBilletera = servicioBilletera;
		this.servicioRegistro = servicioRegistro;
		this.servicioEstacionamiento = servicioEstacionamiento;
	}
	
	@RequestMapping(path="/mostrarClientes", method=RequestMethod.GET)
		public String clientes(Model modelo,
				HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
			modelo.addAttribute("clientes", servicioLogin.listaDeClientes());
			
			servicioRegistro.NotificacionesVistas();
			return("ListaClientes");
			}
		return ("redirect:/login");
	}
	@RequestMapping(path="/mostrarAutosClientes/{id}", method=RequestMethod.GET)
	public ModelAndView AutosDeClientes(
			@PathVariable("id")Long id, 
			HttpServletRequest request) {
		
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente") || rol.equals("admin")) {
		ModelMap modelo = new ModelMap();
		
		Cliente cliente = servicioLogin.consultarClientePorId(id);
		
		modelo.put("cantidad", servicioAuto.consultarAutoDeClienteActivo(cliente).size());
		modelo.put("auto", servicioAuto.consultarAutoDeClienteActivo(cliente));
		
		modelo.put("cliente", cliente);
		return new ModelAndView("ListaAutosDeClienteAgregar", modelo);
		}
		return new ModelAndView("redirect:/login");
}
	
	@RequestMapping(path="/mostrarAutosClientes/eliminar/{id}/{idC}")
	public ModelAndView eliminarAuto(@PathVariable("id")Long id,@PathVariable("idC")Long idC) {
		ModelMap modelo = new ModelMap();
		Auto auto=servicioAuto.buscarAuto(id);
		
		servicioAuto.eliminarAuto(auto);
		return new ModelAndView("redirect:/mostrarAutosClientes/{idC}");
	}
	
	/*@RequestMapping(path="/misAutos/{id}", method=RequestMethod.GET)
	public ModelAndView misAutos(
			@PathVariable("id")Long id, 
			HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioLogin.consultarClientePorId(id);
		List<Auto> autos = servicioAuto.consultarAutoDeCliente(cliente);
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null )
			if(rol.equals("cliente") && cliente != null && autos != null) {	
				modelo.put("cliente", cliente);
				modelo.put("auto", autos);
		return new ModelAndView("ListaAutosDeClienteAgregar", modelo);
		}
		return new ModelAndView("redirect:/login");
}
	*/
	@RequestMapping(path="/datosCliente/{id}")
	public ModelAndView mostrarDatosCliente(@PathVariable("id")Long id) {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		List<Estacionamiento> estacionamiento = servicioEstacionamiento.buscarEstacionamientoPorCliente(cliente);
		modelo.put("cliente", cliente);
		modelo.put("billetera", billetera);
		modelo.put("estacionamiento", estacionamiento);
		modelo.put("reservas", servicioEstacionamiento.buscarEstacionamientoPorCliente(cliente));
		return new ModelAndView("miPerfil", modelo);
	}
			
	
		
	

}
