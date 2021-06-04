package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor como mecanismo de inyección de dependencias,
	// es decir, qeue lo parametros del mismo deben ser un bean de spring y el framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente la interface ServicioLogin,
	// dicha clase debe estar anotada como @Service o @Repository y debe estar en un paquete de los indicados en
	// applicationContext.xml
	private ServicioLogin servicioLogin;
	private ServicioAuto servicioAuto;
	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin, ServicioAuto servicioAuto){
		this.servicioLogin = servicioLogin;
		this.servicioAuto =servicioAuto;
	}

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Cliente cliente = new Cliente();
		modelo.put("cliente", cliente);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("login", modelo);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Cliente cliente, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		
		Cliente usuarioBuscado = servicioLogin.consultarCliente(cliente);
		String rol = (String) request.getSession().getAttribute("roll");
		if (usuarioBuscado != null) {
			if(usuarioBuscado.getRoll().equals("admin")) {
				
				request.getSession().setAttribute("roll", usuarioBuscado.getRoll());
				model.put("admin", usuarioBuscado);
				return new ModelAndView("homeAdmin", model);
				
			}else {
				
				request.getSession().setAttribute("roll", usuarioBuscado.getRoll());
				model.put("cliente", usuarioBuscado);
				return new ModelAndView("home", model);
			}
	
		}else {
			model.put("Error", "Usuario o clave incorrecta");
		//	 si el usuario no existe agrega un mensaje de error en el modelo.
		}
		return new ModelAndView("redirect:/login", model);
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	/*@RequestMapping(path = "/home", method = {RequestMethod.GET, RequestMethod.PUT})
	public ModelAndView irAHome() {
		return new ModelAndView("home");
	}
	*/
	/*@RequestMapping(path = "/homeAdmin", method = {RequestMethod.GET, RequestMethod.PUT})
	public ModelAndView irAHomeAdmin() {
		return new ModelAndView("homeAdmin");
	}
	*/

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path = "/cerrarSesion", method = RequestMethod.GET)
	public ModelAndView cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/login");
	}
	
	
}
