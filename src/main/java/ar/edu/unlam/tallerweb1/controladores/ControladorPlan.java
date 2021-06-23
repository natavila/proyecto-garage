package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(path = "/planes", method = RequestMethod.GET)
	public ModelAndView planes() {
		ModelMap modelo = new ModelMap();
		modelo.put("planes", servicioPlan.obtenerPlanes());

		return new ModelAndView("planes", modelo);
	}

	@RequestMapping(path = "/planes/ {planId}", method = RequestMethod.GET)
    public ModelAndView elegirPlan(@ModelAttribute("plan") Plan plan,HttpServletRequest request ) { {

		ModelMap modelo = new ModelMap();
		Cliente c1 =(Cliente) request.getAttribute("cliente");
		servicioCliente.consultarCliente(c1);
		
		
		try {
			if (plan.getCliente().equals(null)) {
				plan.setCliente(c1);

				Plan p1 = servicioPlan.existeClienteConPlan(c1, plan);

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
		return new ModelAndView("planes", modelo);
		
	}

}
}
