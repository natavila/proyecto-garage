package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor como mecanismo de inyección de dependencias,
	// es decir, qeue lo parametros del mismo deben ser un bean de spring y el framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente la interface ServicioLogin,
	// dicha clase debe estar anotada como @Service o @Repository y debe estar en un paquete de los indicados en
	// applicationContext.xml
	private ServicioLogin servicioLogin;
	private ServicioEstacionamiento servEst;
	private ServicioBilletera servicioBilletera;
	private ServicioGarage servicioGarage;
	private ServicioCliente servicioCliente;
	private ServicioRegistro servicioRegistro;
	@Autowired

	public ControladorLogin(ServicioLogin servicioLogin, ServicioRegistro servicioRegistro,ServicioCliente servicioCliente,ServicioBilletera servicioBilletera,ServicioGarage servicioGarage,ServicioEstacionamiento servEst){

		this.servicioLogin = servicioLogin;
		this.servicioBilletera = servicioBilletera;
		this.servEst= servEst;
		this.servicioGarage = servicioGarage;
		this.servicioCliente = servicioCliente;
		this.servicioRegistro = servicioRegistro;
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

		//String rol = (String) request.getSession().getAttribute("roll");
		if (usuarioBuscado != null) {
			model.put("cliente", usuarioBuscado);
			return new ModelAndView("redirect:/home/{cliente.id}");
			
	
		}else {
			model.put("usuario", usuarioBuscado);
			model.put("Error", "Usuario o clave incorrecta");
		}
			
			return new ModelAndView("login", model);
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home/{cliente.id}", method = RequestMethod.GET)
	public ModelAndView irAHome(@PathVariable("cliente.id")Long id, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Cliente usuarioBuscado = servicioCliente.consultarClientePorId(id);
		if(usuarioBuscado != null) {
			if(usuarioBuscado.getRoll() == "admin") {
				model.put("admin", usuarioBuscado);
				request.getSession().setAttribute("roll", usuarioBuscado.getRoll());
				
				model.put("admin", usuarioBuscado);
				
				//List<Garage> listaGarage = servicioGarage.consultarGarage();
				ArrayList<Integer> ocupacion = new ArrayList<Integer>();
				
				Integer notifNuevos = servicioRegistro.NotificacionesClientes();
				
				for(Garage e: servicioGarage.consultarGarage()) {
					
					ocupacion.add(servicioGarage.cantidadDeLugarEnEst(e));	
				}
				
				for(Integer e: ocupacion) {
					if(e<=5 && e>=1 ) {
						model.put("alerta","mensaje");
						break;
					}else if(e<=0){
						model.put("Lleno", "mensaje");
						break;
					}	else {
						model.put("ConLugar", "ConLugar");
					}
				}
				
				Integer notif = servicioCliente.notificadorDeClientesNuevos();


				model.put("notifNuevos", notifNuevos);

				
				model.put("fecha", LocalDate.now());
				
				
				model.put("notifNuevos", servicioRegistro.NotificacionesClientes());

				model.put("notif", notif);
				model.put("ocupacion", ocupacion);
				
				model.put("garages",/*listaGarage*/ servicioGarage.consultarGarage());
				model.put("ganancia",servEst.dineroGanadoEnTotal() );
				
				return new ModelAndView("homeAdmin", model);
			}else {
				model.put("cliente", usuarioBuscado);
				request.getSession().setAttribute("roll", usuarioBuscado.getRoll());			

				Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(usuarioBuscado);
				List<Garage> listaGarage = servicioGarage.consultarGarage();
				List<Garage> garagesCercanos = servicioGarage.buscarGarageQueCoincidanConLocalidadDeCliente(usuarioBuscado);
				request.getSession().setAttribute("roll", usuarioBuscado.getRoll());

				model.put("cliente", usuarioBuscado);
				model.put("billetera", billetera);
				model.put("plan",usuarioBuscado.getPlan());
				model.put("garages", listaGarage);
				model.put("garagesCercanos", garagesCercanos);
	
				return new ModelAndView("home", model);


			}	
	
		}

		return new ModelAndView("login", model);		
			
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		ModelMap model = new ModelMap();
	
		return new ModelAndView("home", model);

	}

	
	

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
	
	
	public ServicioLogin getServicioLogin() {
		return servicioLogin;
	}



	public void setServicioLogin(ServicioLogin servicioLogin) {
		this.servicioLogin = servicioLogin;
	}



	public ServicioEstacionamiento getServEst() {
		return servEst;
	}



	public void setServEst(ServicioEstacionamiento servEst) {
		this.servEst = servEst;
	}



	public ServicioBilletera getServicioBilletera() {
		return servicioBilletera;
	}



	public void setServicioBilletera(ServicioBilletera servicioBilletera) {
		this.servicioBilletera = servicioBilletera;
	}



	public ServicioGarage getServicioGarage() {
		return servicioGarage;
	}



	public void setServicioGarage(ServicioGarage servicioGarage) {
		this.servicioGarage = servicioGarage;
	}



	public ServicioCliente getServicioCliente() {
		return servicioCliente;
	}



	public void setServicioCliente(ServicioCliente servicioCliente) {
		this.servicioCliente = servicioCliente;
	}



	public ServicioRegistro getServicioRegistro() {
		return servicioRegistro;
	}



	public void setServicioRegistro(ServicioRegistro servicioRegistro) {
		this.servicioRegistro = servicioRegistro;
	}
	
}
