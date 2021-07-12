package ar.edu.unlam.tallerweb1.controladores;

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
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;

@Controller
public class ControladorAdmin {

	private ServicioPlan servicioPlan;
	private ServicioLogin servicioLogin;
	private ServicioAuto servicioAuto;

	@Autowired
	public ControladorAdmin(ServicioPlan servicioPlan, ServicioLogin servicioLogin, ServicioAuto servicioAuto) {
		this.servicioPlan = servicioPlan;
		this.servicioLogin = servicioLogin;
		this.servicioAuto = servicioAuto;
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
}
