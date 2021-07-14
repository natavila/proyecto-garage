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
	@RequestMapping(path="/misAutos", method=RequestMethod.GET)
	public ModelAndView misAutos( HttpServletRequest request) {
		
		ModelMap modelo = new ModelMap();
		String rol = (String) request.getSession().getAttribute("roll");
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		
		if(cliente != null)
			
			if(rol.equals("cliente")) {
		
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


		servicioAuto.SacarAuto(auto);
		auto.setEnUso(false);
		
		servicioAuto.eliminarAuto(auto);

		try {
			modelo.put("auto", auto);
			servicioAuto.eliminarAuto(auto);
		}catch (Exception e) {
			modelo.put("auto", auto);
			modelo.put("exception", e.getMessage());
		}
		

		return new ModelAndView("redirect:/misAutos/{idC}");
	}
	
	/*@RequestMapping(path="/misAutos/{id}", method=RequestMethod.GET)
	public ModelAndView misAutos(
			@PathVariable("id")Long id, 
			HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioLogin.consultarClientePorId(id);
		List<Auto> autos =  servicioAuto.consultarAutoDeClienteActivo(cliente);
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null )
			if(rol.equals("cliente") && cliente != null && autos != null) {	
				modelo.put("cliente", cliente);
				modelo.put("auto", autos);
				modelo.put("cantidad", servicioAuto.consultarAutoDeClienteActivo(cliente).size());
		return new ModelAndView("ListaAutosDeClienteAgregar", modelo);
		}
		return new ModelAndView("redirect:/login");
}
	*/
	@RequestMapping(path="/datosCliente")
	public ModelAndView mostrarDatosCliente(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		if(cliente != null && billetera != null) {
			List<Estacionamiento> estacionamiento = servicioEstacionamiento.buscarEstacionamientoPorCliente(cliente);
			modelo.put("cliente", cliente);
			modelo.put("billetera", billetera);
			modelo.put("estacionamiento", estacionamiento);
			modelo.put("reservas", servicioEstacionamiento.buscarEstacionamientoPorCliente(cliente));
			
			return new ModelAndView("miPerfil", modelo);
		}else {
			
			return new ModelAndView("redirect:/login");
		}
		
	}
	
	@RequestMapping(path="/modificarCliente")
	public ModelAndView modificarCliente(HttpServletRequest request) {
		
		ModelMap modelo = new ModelMap();
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		
		if(cliente != null) {
			modelo.put("cliente", cliente);
			return new ModelAndView("modificarCliente", modelo);
		}
		
		return new ModelAndView("redirect:/login");
		
	}
	
	@RequestMapping(path="/procesarModificarCliente")
	public ModelAndView procesarModificarCliente(@ModelAttribute("cliente") Cliente cliente, HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente clienteBuscado = servicioCliente.consultarClientePorId(idUsuario);
		
		if(clienteBuscado != null) {
			
			servicioCliente.modificarDatosCliente(cliente, clienteBuscado);
			modelo.put("cliente", clienteBuscado);
			return new ModelAndView("redirect:/datosCliente");
		}
		
		return new ModelAndView("redirect:/login");
	}
	
	 //Tickets De clientes
	
	@RequestMapping(path="/ticketsCliente")
	public ModelAndView mostrarTicketCliente(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Long id = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		List<Estacionamiento> estacionamiento = servicioEstacionamiento.buscarEstacionamientoPorClienteQueTengaReserva(cliente);
		if(cliente != null && billetera != null) {
			
			if(estacionamiento != null) {
				modelo.put("cliente", cliente);
				modelo.put("billetera", billetera);
				modelo.put("estacionamiento", estacionamiento);
				modelo.put("mensaje", "¡No posee reservas activas!");
				return new ModelAndView("ticketCliente", modelo);
			}else {
				modelo.put("cliente", cliente);
				modelo.put("billetera", billetera);
				modelo.put("estacionamiento", estacionamiento);
				modelo.put("mensaje", "¡No posee reservas activas!");
				return new ModelAndView("ticketCliente", modelo);
			}
		
		}else {
			return new ModelAndView("redirect:/login");
		}
		
	}
			
	
		
	

}
