package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPaginas {

	@RequestMapping("/mostrarContacto") //Aca va la ruta y el metodo. Es la ruta que aparece en la URL, tambien llamada action. Si utilizo el metodo GET no hace falta ponerlo ya que se usa por defecto
	public ModelAndView contacto() { //Dentro del controlador se crea un metodo de tipo ModelAndView, el cual nos retornara una vista en este caso.
		return new ModelAndView("contacto"); // "contacto" es el nombre de la vista, el cual es un archivo jsp. Su estructura es de html.
	}
	@RequestMapping(path = "mostrarDatos", method = RequestMethod.GET)
	public ModelAndView datos() {
		ModelMap modelo = new ModelMap(); //Se crea un modelo de tipo Map
		modelo.put("materia", "Taller Web I"); //Se le agrega los paramatros. El primero es la clave y el segundo el valor.
		modelo.put("lenguaje", "Java");
		return new ModelAndView("datos", modelo); //Se pasa la vista datos y los datos de modelo.
	}
	@RequestMapping(path = "mostrarConfirmacion", method = RequestMethod.GET)
	public ModelAndView confirmacion(
			@RequestParam(value = "nombre", required = false) String name, //Se el required es true si el dato viene estalla(produce una excepcion en servidor y al usuario le muestra un error de tipo 500), el false lo hace opcional
			@RequestParam(value = "apellido", required = false) String surname
			) {
			ModelMap modelo = new ModelMap();
			modelo.put("nombre", name.toUpperCase());
			modelo.put("apellido", surname);
			return new ModelAndView("confirmacion", modelo);
		
	}
	
}
