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
	
	@RequestMapping(path="/registroBilletera/{id}",  method=RequestMethod.GET)
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
	
	@RequestMapping(path="/procesarRegistroBilletera/{id}", method=RequestMethod.GET)
	public ModelAndView procesarRegistro(@PathVariable("id") Long id,
										@ModelAttribute("billetera") Billetera billetera) {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Billetera billeteraEncontrada = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		try {
			if(cliente != null && billeteraEncontrada == null) {
				billetera.setSaldo(0.0);
				billetera.setCliente(cliente);
				servicioBilletera.registrarBilletera(billetera);
				modelo.put("cliente", cliente);
				modelo.put("billetera", billetera);
				modelo.put("saldo", billetera.getSaldo());
				
				return new ModelAndView("confirmacionBilletera", modelo);
		}else {
			modelo.put("cliente", cliente);
			modelo.put("error", "Usted ya posee una billetera");
		}
		
		}catch(Exception e) {
			
			modelo.put("billetera", billetera);
			modelo.put("error", e.getMessage());
			
		}
		
		return new ModelAndView("registroBilletera", modelo);
		
	}
	
	@RequestMapping("/mostrarBilletera/{id}")
	public ModelAndView mostrarBilletera(@PathVariable("id") Long id) {

		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		try {
			
			if(cliente != null && billetera != null) {
				modelo.put("saldo", billetera.getSaldo());
				modelo.put("nombre", cliente.getNombre());
				modelo.put("apellido", cliente.getApellido());
				
				modelo.put("cliente", cliente);
			
			return new ModelAndView("miBilletera", modelo);
		}else {
			
			modelo.put("mensaje", "Usted no posee una billetera. Por favor, genere una");
		}
			
			}catch(Exception e) {
				modelo.put("billetera", billetera);
				modelo.put("error", e.getMessage());				
	
			}
		
		return new ModelAndView("redirect:/registroBilletera/{id}", modelo);
	}
	
	@RequestMapping(path="/formularioSaldo/{id}", method=RequestMethod.GET)
	public ModelAndView formularioSaldo(@PathVariable("id") Long id,
										@ModelAttribute("billetera") Billetera billetera) {
		ModelMap modelo = new ModelMap();
		
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		try{
			if(cliente != null && billetera != null)
		
			modelo.put("cliente", cliente);
			return new ModelAndView("ingresarSaldo", modelo);	
		} catch(Exception e) {
			
			modelo.put("cliente", cliente);
			modelo.put("error", e.getMessage());
		}
			return new ModelAndView("redirect:/formularioSaldo/{id}");
	}
	
	@RequestMapping(path="/procesarSaldo/{id}", method=RequestMethod.POST)
	public ModelAndView ingresarSaldo(@PathVariable("id") Long id,
									@ModelAttribute("billetera") Billetera billetera,
									@RequestParam("monto") Double monto
									) {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		try {
			if(cliente != null && billetera != null) {
				if(monto >= 50) {
					servicioBilletera.ingresarSaldo(billetera, monto);
					modelo.put("billetera", billetera);
					modelo.put("cliente", cliente);
					modelo.put("saldo", billetera.getSaldo());
					
					return new ModelAndView("confirmacionSaldo", modelo);
				}else {
					
					modelo.put("cliente", cliente);
					modelo.put("billetera", billetera);
					modelo.put("error", "Ingrese un monto mayor a $50");
				}					
			}
		} catch(Exception e) {

			modelo.put("exception", e.getMessage());
			modelo.put("error", "Ingrese un monto");
			modelo.put("billetera", billetera);
			modelo.put("cliente", cliente);
			return new ModelAndView("ingresarSaldo", modelo);
		}
		return new ModelAndView("ingresarSaldo", modelo); //ARREGLAR
	}
}
