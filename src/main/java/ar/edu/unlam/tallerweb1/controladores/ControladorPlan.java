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

	@RequestMapping(path = "/planes", method = RequestMethod.GET)
	public ModelAndView planes(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Long idCliente = (Long) request.getSession().getAttribute("id");
		String rol = (String) request.getSession().getAttribute("roll");
		Cliente c1 = servicioCliente.consultarClientePorId(idCliente);
		
		if(c1 != null) {
			if(rol.equals("cliente")) {
				if(c1.getPlan() == null) {
					modelo.put("cliente", c1);
					modelo.put("planes", servicioPlan.obtenerPlanes());
	
					return new ModelAndView("planes", modelo);
				}else {
					modelo.put("cliente", c1);
					return new ModelAndView("redirect:/datosCliente");
				}
			}
		}
			return new ModelAndView("redirect:/login");
	}



	@RequestMapping(path = "/asignarplan/{plan}", method = RequestMethod.GET)
	public ModelAndView elegirPlan(@PathVariable("plan") Long idP, HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		Long idCliente = (Long) request.getSession().getAttribute("id");
		String rol = (String) request.getSession().getAttribute("roll");
		Cliente c1 = servicioCliente.consultarClientePorId(idCliente);
		Plan p1 = servicioPlan.consultarPlan(idP);
			if(c1 != null) {
				if(rol.equals("cliente")) {
					if(c1.getPlan()==null) {
						servicioPlan.asignarPlanACliente(c1, p1);
						modelo.put("mensajeExito", "El plan se asigno correctamente");
						modelo.put("cliente", c1);
						modelo.put("plan", p1);
					}else {
						modelo.put("mensajeTienePlan", "No se pudo Asignar Plan");
						return new ModelAndView("planes", modelo);
					}
				}
			}					
				return new ModelAndView("planes", modelo);
	}

}

