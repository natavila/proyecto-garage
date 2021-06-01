package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;

@Controller
public class ControladorBilletera {

	private ServicioBilletera servicioBilletera;
	private ServicioCliente servicioCliente;
	
	@Autowired
	public ControladorBilletera(ServicioBilletera servicioBilletera, ServicioCliente servicioCliente) {
		
		this.servicioBilletera = servicioBilletera;
		this.servicioCliente = servicioCliente;
	}
	
	@RequestMapping(path="/registroBilletera/{id}")
	public ModelAndView registro(@PathVariable("id")Long id) {

		ModelMap modelo = new ModelMap();
		Billetera billetera = new Billetera();
		
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		if(cliente != null) {
			modelo.addAttribute("cliente", cliente);
			
			billetera.setCliente(cliente);
			modelo.put("billetera", billetera);
			
			return new ModelAndView("registroBilletera", modelo);
		}else {
			
			modelo.put("error", "Cliente no registrado");
		}
		
		return new ModelAndView("registroBilletera", modelo);
	}
	
	@RequestMapping(path="/procesarRegistroBilletera/{id}", method=RequestMethod.POST)
	public ModelAndView procesarRegistro(@PathVariable("id") Long id,
										@ModelAttribute("billetera") Billetera billetera) {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		
		if(cliente != null && billetera.getAlias() != null) {
			billetera.setSaldo(0.0);
			billetera.setCliente(cliente);
			servicioBilletera.registrarBilletera(billetera);
			modelo.addAttribute("cliente", cliente);
			modelo.put("billetera", billetera);
			modelo.put("saldo", billetera.getSaldo());
			
			return new ModelAndView("confirmacionBilletera", modelo);
		}else {
			
			modelo.put("error", "Cliente no registrado");
		}
		
		return new ModelAndView("confirmacionBilletera", modelo);
	}
	
	@RequestMapping("/mostrarBilletera/{id}")
	public ModelAndView mostrarBilletera(@PathVariable("id") Long id) {

		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		
			modelo.put("cliente", cliente);
			modelo.put("alias", billetera.getAlias());
			return new ModelAndView("miBilletera");
		
		
	}
	
	@RequestMapping("/ingresarSaldo/{id}")
	public ModelAndView ingresarSaldo(@PathVariable("id") Long id,
									@RequestParam("monto") Double monto) {
		ModelMap modelo = new ModelMap();
		
		Billetera billetera = servicioBilletera.buscarBilleteraPorId(id);
		
		if(billetera != null) {
			billetera.setSaldo(monto);
			modelo.put("saldo", billetera.getSaldo());
			
		}
		
		return new ModelAndView("redirect:/home");
	}
}
