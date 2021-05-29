package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.xdevapi.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorClientes {
	
	private ServicioLogin servicioLogin;
	
	@Autowired
	private ControladorClientes(ServicioLogin servicioLogin) {
		
		this.servicioLogin = servicioLogin;
	}
	
	@RequestMapping(path="/mostrarClientes", method=RequestMethod.GET)
		public String clientes(Model modelo) {
			modelo.addAttribute("clientes", servicioLogin.listaDeClientes());
			return("ListaClientes");
	}
			
	
		
	

}
