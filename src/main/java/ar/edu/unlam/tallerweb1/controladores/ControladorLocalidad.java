package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioLocalidad;

@Controller
	public class ControladorLocalidad {
		private ServicioLocalidad servicioLoc;

		@Autowired 
		public ControladorLocalidad(ServicioLocalidad servicioLoc) {
			this.servicioLoc = servicioLoc;

		}

	@RequestMapping(path="/mostrarRegistroLocalidad")
	public ModelAndView registroLocalidad(HttpServletRequest request, ModelMap modelo) {
		String rol = (String) request.getSession().getAttribute("roll");
		ModelMap model = new ModelMap();
		Localidad loc = new Localidad();
		if(rol != null)
			if(rol.equals("admin")) {
				model.put("localidad", loc);
				return new ModelAndView ("registroLocalidad", model);
			}else {
				model.put("error", "Localidad No Registrada");

			}

		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(path="/procesarRegistroLocalidad")
	public ModelAndView procesarRegistroLocalidad(HttpServletRequest request, 
			@ModelAttribute("localidad") Localidad loc) {
		ModelMap modelo = new ModelMap();
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("admin")) {
				if(servicioLoc.consultarUnaLocalidad(loc)==null && servicioLoc.verificarSiExisteLocalidad(loc).equals(false)) {
					servicioLoc.agregarLocalidad(loc);
					modelo.put("error", "Localidad Registrada Correctamente");
					return new ModelAndView("confirmacionRegistroLocalidad", modelo);
			}else {
				modelo.put("error", "Error al registrar Localidad");
				return new ModelAndView("confirmacionRegistroLocalidad", modelo);
			}
			}

		return new ModelAndView("redirect:/login");
	}




	}
	

