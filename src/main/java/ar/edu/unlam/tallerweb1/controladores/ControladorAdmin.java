package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;

@Controller
public class ControladorAdmin {

	private ServicioPlan servicioPlan;

	@Autowired
	public ControladorAdmin(ServicioPlan servicioPlan) {
		this.servicioPlan = servicioPlan;
	}

	@RequestMapping(path = "/planes", method = RequestMethod.GET)
	public ModelAndView planes() {
		ModelMap modelo = new ModelMap();
		modelo.put("planes", servicioPlan.obtenerPlanes());

		return new ModelAndView("planes", modelo);
	}

}
