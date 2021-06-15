package ar.edu.unlam.tallerweb1.controladores;

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
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorClientes {
	
	private ServicioLogin servicioLogin;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	
	@Autowired
	private ControladorClientes(ServicioLogin servicioLogin,ServicioAuto servicioAuto,ServicioCliente servicioCliente) {
		this.servicioAuto =servicioAuto;
		this.servicioLogin = servicioLogin;
		this.servicioCliente = servicioCliente;
	}
	
	@RequestMapping(path="/mostrarClientes", method=RequestMethod.GET)
		public String clientes(Model modelo,
				HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
			modelo.addAttribute("clientes", servicioLogin.listaDeClientes());
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
		modelo.put("cantidad", servicioAuto.consultarAutoDeCliente(cliente).size());
		modelo.put("auto",servicioAuto.consultarAutoDeCliente(cliente));
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
	
	
			
	
		
	

}
