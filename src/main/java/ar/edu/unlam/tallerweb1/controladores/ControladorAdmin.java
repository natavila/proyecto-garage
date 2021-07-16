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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLocalidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

@Controller
public class ControladorAdmin {

	private ServicioPlan servicioPlan;
	private ServicioLogin servicioLogin;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	private ServicioGarage servicioGarage;
	private ServicioEstacionamiento servicioEst;
	private ServicioLocalidad servicioLoc;
	private ServicioRegistro servicioRegistro;
	@Autowired
	public ControladorAdmin( ServicioRegistro servicioRegistro, ServicioLocalidad servicioLoc,ServicioEstacionamiento servicioEst,ServicioGarage servicioGarage, ServicioCliente servicioCliente,ServicioPlan servicioPlan, ServicioLogin servicioLogin, ServicioAuto servicioAuto) {
		this.servicioPlan = servicioPlan;
		this.servicioLogin = servicioLogin;
		this.servicioAuto = servicioAuto;
		this.servicioCliente = servicioCliente;
		this.servicioGarage = servicioGarage;
		this.servicioEst = servicioEst;
		this.servicioLoc = servicioLoc;
		this.servicioRegistro = servicioRegistro;
	}
	
	@RequestMapping("/lista")
	public ModelAndView Listar(HttpServletRequest request){
		String rol = (String) request.getSession().getAttribute("roll");
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		
		if(cliente != null)
			if(rol.equals("admin")) {
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("garages", servicioGarage.consultarGarage());
		return new ModelAndView("DatosGaragesPorPantalla", modelo);
			}
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path="AdministrarGarage/{id}", method = RequestMethod.GET)
	public ModelAndView administrarGarage(HttpServletRequest request,
			@PathVariable("id") Long garage1){
		String rol = (String) request.getSession().getAttribute("roll");
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		ModelMap modelo = new ModelMap();
		Garage garage = servicioGarage.buscarGarage(garage1);
		if(cliente != null)
			if(rol.equals("admin")) {
				ArrayList<Auto> autos = (ArrayList<Auto>) servicioEst.buscarAutosQueEstenActivosEnUnGarage(garage);
				modelo.put("garage", garage);
				modelo.put("lugar", servicioGarage.cantidadDeLugarEnEst(garage));
				modelo.put("dinero", servicioEst.dineroGanadoEnElDia(garage));
				modelo.put("autos",autos);
				ArrayList<Long> tickets = servicioEst.numeroDeTicketAuto(garage);
				modelo.put("tickets",tickets );
				if(servicioGarage.cantidadDeLugarEnEst(garage)<=5 && servicioGarage.cantidadDeLugarEnEst(garage)>=1) {
					modelo.put("alerta", "Garage Con Pocos Lugares Disponibles");
				}else if(servicioGarage.cantidadDeLugarEnEst(garage)<=0) {
					modelo.put("Lleno", "Garage Sin Lugares");
					
				}else {
					modelo.put("ConLugar", "Con lugar");
				}
				
				
				return new ModelAndView ("AdministrarGarage", modelo);
			}
		return new ModelAndView("redirect:/login");
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
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		if(cliente != null)
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
	
	@RequestMapping(path="/mostrarHistoricoDeUnGarage/{id}", method=RequestMethod.GET)
	public ModelAndView MostrarHistoricoDeGarage( @PathVariable("id")Long id,
			HttpServletRequest request){
		
		String rol = (String) request.getSession().getAttribute("roll");
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		if(cliente != null)
			if(rol.equals("admin")) {
		    ModelMap modelo = new ModelMap();
			Garage garage2 = servicioGarage.buscarGarage(id);
			List<Auto> autos = servicioEst.buscarAutosDeUnGarage(garage2);
			
			modelo.put("garage", garage2);
			modelo.put("auto", autos);
			
			return new ModelAndView("ListaHistoricaDeAutosEnGarage", modelo);
			}
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping("/crearPlan")
	public ModelAndView registroPlan() {
		ModelMap modelo = new ModelMap();
		Plan plan = new Plan();
		modelo.put("plan", plan);
		return new ModelAndView("crearPlan", modelo);

	}

	@RequestMapping(path = "/crearPlan", method = RequestMethod.POST)
	public ModelAndView validacionPlan(@ModelAttribute("plan") Plan plan) {

		ModelMap modelo = new ModelMap();
		servicioPlan.consultarPlan(plan.getId());

		try {
			if (plan.getNombre() != "" && plan.getCantidadAutosPermitidos() > 0
					&& plan.getCantidadHorasPermitidas() > 0) {
				servicioPlan.crearPlan(plan);
				modelo.put("mensajeOk", "Plan creado correctamente");

			} else {
				modelo.put("mensajeError", "Error al crear el plan");
			}
		} catch (Exception e) {
			modelo.put("mensajeError", "Error al crear el plan");
			System.out.println(e.getMessage());
		}

		return new ModelAndView("crearPlan", modelo);
	}

	
	@RequestMapping(path="/misAutos/{id}", method=RequestMethod.GET)
	public ModelAndView AutosDeClientes(
			@ModelAttribute("usuario") Cliente cliente,
			@PathVariable("id")Long id, 
			HttpServletRequest request) {
		
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente") || rol.equals("admin")) {
		ModelMap modelo = new ModelMap();
		
		Cliente cliente1 = servicioLogin.consultarClientePorId(id);
		
		modelo.put("cantidad", servicioAuto.consultarAutoDeClienteActivo(cliente1).size());
		modelo.put("auto", servicioAuto.consultarAutoDeClienteActivo(cliente1));
		modelo.put("cliente", cliente);
		modelo.put("cliente1", cliente1);
		return new ModelAndView("ListaAutosDeClienteAgregar", modelo);
		}
		return new ModelAndView("redirect:/login");
}
	
	@RequestMapping("/formularioAgregarGarage")
	public ModelAndView mostrarFormularioGaraga(HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("roll");
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		if(cliente != null)
			if(rol.equals("admin")) {
				ModelMap modelo = new ModelMap();
				Garage garage1 = new Garage();
				modelo.put("garage", garage1);
				
				List <String> loc = servicioLoc.devolverNombresDeLocalidades();
				modelo.put("loc", loc);
				
				return new ModelAndView("agregarGarage", modelo);
			}
		return new ModelAndView("redirect:/login");	
		
	}
	
	
	
	@RequestMapping(path="confirmarAgregarGarage", method = RequestMethod.POST)
	public String agregarGarage(HttpServletRequest request,
	@ModelAttribute ("garage") Garage garage1){
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
	servicioGarage.agregarGarage(garage1);
	return "redirect:/lista";
			}
		return ("redirect:/login");
	}
	
	@RequestMapping("/lista/eliminar/{id}")
	public ModelAndView eliminaGarge(@PathVariable("id")Long id) {
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("garage", servicioGarage.eliminarGarage(id));
		return new ModelAndView("redirect:/lista", modelo);
	}
	
	@RequestMapping(path="/modificarGarage/{id}")
	public ModelAndView modificarGarage(HttpServletRequest request, 
			@PathVariable("id") Long garage1) {
		
		ModelMap modelo = new ModelMap();
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario); 
		Garage garage = servicioGarage.buscarGarage(garage1);
		if(cliente != null) {
			modelo.put("garage", garage);
			return new ModelAndView("modificarGarage", modelo);
		}
		
		return new ModelAndView("redirect:/login");
		
	}
	@RequestMapping(path="/mostrarClientes", method=RequestMethod.GET)
	public ModelAndView clientes(Model modelo,
			HttpServletRequest request) {
	String rol = (String) request.getSession().getAttribute("roll");
	Long idUsuario = (Long) request.getSession().getAttribute("id");
	Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
	if(cliente != null)
		if(rol.equals("admin")) {
		modelo.addAttribute("clientes", servicioLogin.listaDeClientes());
		
		servicioRegistro.NotificacionesVistas();
		 return new ModelAndView("ListaClientes");
		}
	return new ModelAndView("redirect:/login");
}

@RequestMapping("/mostrarClientes/eliminar/{id}")
public ModelAndView eliminaCliente(@PathVariable("id")Long id) {
	ModelMap modelo = new ModelMap();
	modelo.addAttribute("cliente", servicioCliente.eliminarCliente(id));
	return new ModelAndView("redirect:/mostrarClientes", modelo);
}


	@RequestMapping(path="/procesarModificarGarage")
	public ModelAndView procesarModificarCliente(@ModelAttribute("garage") Garage garage,HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		Garage garageBuscado = servicioGarage.contultarUnGarage(garage);
		if(cliente != null) {
			servicioGarage.modificarGarage(garage, garageBuscado);
			
			modelo.put("garage", garageBuscado);
			return new ModelAndView("redirect:/lista");
		}
		
		return new ModelAndView("redirect:/login");
	}
	
	
	
	@RequestMapping(path="/misAutosAdmin/{id}", method=RequestMethod.GET)
	public ModelAndView misAutosAdmin( HttpServletRequest request, 
			@PathVariable("id")Long id) {
		
		ModelMap modelo = new ModelMap();
		String rol = (String) request.getSession().getAttribute("roll");
		Long idUsuario = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idUsuario);
		Cliente cliente2 = servicioCliente.consultarClientePorId(id);
		if(cliente != null) {
			if(rol.equals("admin")) {
		
		modelo.put("cantidad", servicioAuto.consultarAutoDeClienteActivo(cliente2).size());
		modelo.put("auto", servicioAuto.consultarAutoDeClienteActivo(cliente2));
		modelo.put("cliente", cliente2);
		modelo.put("mensaje", "Registre sus autos para realizar una reserva.");
		
		return new ModelAndView("ListaAutosDeClienteAdmin", modelo);
		
			}
		}
		return new ModelAndView("redirect:/login");
}
	
	@RequestMapping(path="/mostrarAutosClientesAdmin/eliminar/{id}/{idC}")
	public ModelAndView eliminarAutoAdmin(@PathVariable("id")Long id,@PathVariable("idC")Long idC) {
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
		

		return new ModelAndView("redirect:/misAutosAdmin/{idC}");
	}

	public ServicioPlan getServicioPlan() {
		return servicioPlan;
	}

	public void setServicioPlan(ServicioPlan servicioPlan) {
		this.servicioPlan = servicioPlan;
	}

	public ServicioLogin getServicioLogin() {
		return servicioLogin;
	}

	public void setServicioLogin(ServicioLogin servicioLogin) {
		this.servicioLogin = servicioLogin;
	}

	public ServicioAuto getServicioAuto() {
		return servicioAuto;
	}

	public void setServicioAuto(ServicioAuto servicioAuto) {
		this.servicioAuto = servicioAuto;
	}

	public ServicioCliente getServicioCliente() {
		return servicioCliente;
	}

	public void setServicioCliente(ServicioCliente servicioCliente) {
		this.servicioCliente = servicioCliente;
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

	public ServicioLocalidad getServicioLoc() {
		return servicioLoc;
	}

	public void setServicioLoc(ServicioLocalidad servicioLoc) {
		this.servicioLoc = servicioLoc;
	}

	public ServicioRegistro getServicioRegistro() {
		return servicioRegistro;
	}

	public void setServicioRegistro(ServicioRegistro servicioRegistro) {
		this.servicioRegistro = servicioRegistro;
	}
	
	
}
