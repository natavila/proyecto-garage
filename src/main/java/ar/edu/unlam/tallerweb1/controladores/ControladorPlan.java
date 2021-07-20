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

import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlan;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;

@Controller
public class ControladorPlan {

	private ServicioPlan servicioPlan;
	private ServicioCliente servicioCliente;
	private ServicioBilletera servicioBilletera;

	@Autowired
	public ControladorPlan(ServicioPlan servicioPlan, ServicioCliente servicioCliente, ServicioBilletera servicioBilletera) {
		this.servicioPlan = servicioPlan;
		this.servicioCliente = servicioCliente;
		this.servicioBilletera = servicioBilletera;
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
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(c1);
		if(c1 != null) {
			if(rol.equals("cliente")) {
				if(c1.getPlan() == null) {
						if(billetera != null) {
						modelo.put("billetera", billetera);
						modelo.put("cliente", c1);
						modelo.put("planes", servicioPlan.obtenerPlanes());
						
						return new ModelAndView("planes", modelo);
					}else {
						modelo.put("cliente", c1);
						modelo.put("planes", servicioPlan.obtenerPlanes());
						modelo.put("mensaje", "Usted no posee billetera para pagar el plan. Por favor, genere una");
						return new ModelAndView("planes", modelo);
					}
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
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(c1);
		Plan p1 = servicioPlan.consultarPlan(idP);
			if(c1 != null) {
				if(rol.equals("cliente")) {
					if(c1.getPlan()==null) {
						if(billetera != null) {
							if(billetera.getSaldo() > p1.getPrecio()) {
								servicioPlan.asignarPlanACliente(c1, p1);
								servicioBilletera.pagarPlan(p1, billetera);
								modelo.put("cliente", c1);
								modelo.put("plan", p1);
								return new ModelAndView("redirect:/planAsignadoCorrectamente");
							}else {
								modelo.put("cliente", c1);
								modelo.put("plan", p1);
								modelo.put("fondoInsuficiente", "No posee fondo suficiente para pagar el plan. Recargue dinero");
								return new ModelAndView("redirect:/planes");
							}
						}else {
							modelo.put("cliente", c1);
							modelo.put("plan", p1);
							modelo.put("mensajeSinBilletera", "Usted no posee una billetera para pagar el plan. Por favor, genere una.");
						}
					}else {
						modelo.put("mensajeTienePlan", "Usted ya posee un plan");
						return new ModelAndView("planes", modelo);
					}
				}
			}					
				return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path="/planAsignadoCorrectamente", method= RequestMethod.GET)
	public ModelAndView planAsignadoCorrectamente(HttpServletRequest request) {
		
		ModelMap modelo = new ModelMap();
		Long idCliente = (Long) request.getSession().getAttribute("id");
		String rol = (String) request.getSession().getAttribute("roll");
		Cliente c1 = servicioCliente.consultarClientePorId(idCliente);
		
		if(c1 != null && rol.equals("cliente")) {
			modelo.put("cliente", c1);
			
			return new ModelAndView("planAsignado", modelo);
		}else {
			
			return new ModelAndView("redirect:/login");
		}
	}

}

