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

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlan;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;

@Controller
public class ControladorPlan {

	private ServicioPlan servicioPlan;
	private ServicioCliente servicioCliente;

	@Autowired
	public ControladorPlan(ServicioPlan servicioPlan, ServicioCliente servicioCliente) {
		this.servicioPlan = servicioPlan;
		this.servicioCliente = servicioCliente;
	}
	@RequestMapping(path = "/planesAdmin", method = RequestMethod.GET)
	public ModelAndView planesAdmin(HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
		
		ModelMap modelo = new ModelMap();
		modelo.put("planes", servicioPlan.obtenerPlanes());

		return new ModelAndView("PlanesAdmin", modelo);
			}
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(path = "/planes/{id}", method = RequestMethod.GET)
	public ModelAndView planes(@ModelAttribute("cliente") Cliente cliente,@PathVariable("id") Long id) {
		ModelMap modelo = new ModelMap();
		Cliente c1 = servicioCliente.consultarClientePorId(id);
		
		modelo.put("cliente", c1);
		modelo.put("planes", servicioPlan.obtenerPlanes());

		return new ModelAndView("planes", modelo);
	}


	@RequestMapping(path = "/asignarplan/{cliente}/{plan}", method = RequestMethod.GET)
	public ModelAndView elegirPlan( @PathVariable("cliente") Long idC ,@PathVariable("plan") Long idP/*,@ModelAttribute("cliente") Cliente cliente,@ModelAttribute("plan") Plan plan*/) {

		ModelMap modelo = new ModelMap();

		Cliente c1 = servicioCliente.consultarClientePorId(idC);
		Plan p1 = servicioPlan.consultarPlan(idP);
		

		servicioCliente.consultarCliente(c1);
		

		
		try {
		
			if (c1.getPlan().equals(null)) {
				
				servicioPlan.asignarPlanACliente(c1, p1);


			//	Plan p1 = servicioPlan.existeClienteConPlan(c1, plan);


				modelo.put("mensajeExito", "El plan se asigno correctamente");
				modelo.put("cliente", c1);
				modelo.put("plan", p1);
				
				
				
			} else {
				modelo.put("mensajeTienePlan", "Ya tiene asignado un plan");
			}
		} catch (Exception e) {
			modelo.put("mensajeTienePlan", "Ya tiene asignado un plan");
			System.out.println(e.getMessage());
		
		}
			
		return new ModelAndView("redirect:/planes/{cliente}", modelo);
		
	}

}

