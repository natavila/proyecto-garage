package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping(path="/registroBilletera",  method=RequestMethod.GET)
	public ModelAndView registro(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		Billetera billetera = new Billetera();
		Long id = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		if(cliente != null) {
			modelo.addAttribute("cliente", cliente);
			billetera.setCliente(cliente);
			modelo.put("billetera", billetera);
			
			return new ModelAndView("registroBilletera", modelo);
		}
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path="/procesarRegistroBilletera", method=RequestMethod.GET)
	public ModelAndView procesarRegistro(@ModelAttribute("billetera") Billetera billetera, HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Long id = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Billetera billeteraEncontrada = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		try {
			if(cliente != null) {
				if(billeteraEncontrada == null) {
					billetera.setSaldo(0.0);
					billetera.setCliente(cliente);
					servicioBilletera.registrarBilletera(billetera);
					modelo.put("cliente", cliente);
					modelo.put("billetera", billetera);
					modelo.put("saldo", billetera.getSaldo());
					
					return new ModelAndView("confirmacionBilletera", modelo);
			}else {
				
				return new ModelAndView("redirect:/home");
			}
				
		}else {
			
			return new ModelAndView("redirect:/login");
		}
		
		}catch(Exception e) {
			
			modelo.put("billetera", billetera);
			modelo.put("error", e.getMessage());
			
		}
		
		return new ModelAndView("registroBilletera", modelo);
		
	}
	
	
	@RequestMapping("/mostrarBilletera")
	public ModelAndView mostrarBilletera(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		Long id = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		try {
			
			if(cliente != null) {
				if(billetera != null) {
					modelo.put("saldo", billetera.getSaldo());
					modelo.put("nombre", cliente.getNombre());
					modelo.put("apellido", cliente.getApellido());
					
					modelo.put("cliente", cliente);
					return new ModelAndView("miBilletera", modelo);		
			
				}else {
			
					modelo.put("mensaje", "Usted no posee una billetera. Por favor, genere una");
				}
				
			}else {
				
				return new ModelAndView("redirect:/login");
			}
			
			}catch(Exception e) {
				modelo.put("billetera", billetera);
				modelo.put("error", e.getMessage());				
	
			}
		
		return new ModelAndView("redirect:/registroBilletera", modelo);
	}
	
	@RequestMapping(path="/formularioSaldo", method=RequestMethod.GET)
	public ModelAndView formularioSaldo(@ModelAttribute("billetera") Billetera billetera, HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Long id = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		try{
			if(cliente != null) {
				if(billetera != null){
					modelo.put("cliente", cliente);
					return new ModelAndView("ingresarSaldo", modelo);	
				}
			}else {
				
				return new ModelAndView("redirect:/login");
			}
		
			
		} catch(Exception e) {
			
			modelo.put("cliente", cliente);
			modelo.put("error", e.getMessage());
		}
			return new ModelAndView("redirect:/formularioSaldo");
	}
	
	@RequestMapping(path="/procesarSaldo", method=RequestMethod.POST)
	public ModelAndView ingresarSaldo(@ModelAttribute("billetera") Billetera billetera,
									@RequestParam("monto") Double monto, HttpServletRequest request
									) {
		ModelMap modelo = new ModelMap();
		Long id = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		try {
			if(cliente != null) {
				if(billetera != null) {
					if(monto >= 50) {
						servicioBilletera.ingresarSaldo(billetera, monto);
						modelo.put("billetera", billetera);
						modelo.put("cliente", cliente);
						modelo.put("saldo", billetera.getSaldo());
						
						return new ModelAndView("redirect:/dineroCargadoExitosamente");
					}else {
						
						modelo.put("cliente", cliente);
						modelo.put("billetera", billetera);
						modelo.put("error", "Ingrese un monto mayor a $50");
						return new ModelAndView("ingresarSaldo", modelo);
					}					
				}
				
			}
			
		} catch(Exception e) {

			modelo.put("exception", e.getMessage());
			modelo.put("error", "Ingrese un monto");
			modelo.put("billetera", billetera);
			modelo.put("cliente", cliente);
			return new ModelAndView("ingresarSaldo", modelo);
		}
		
		return new ModelAndView("redirect:/login"); 
	}
	
	@RequestMapping(path="/dineroCargadoExitosamente", method= RequestMethod.GET)
	public ModelAndView mostrarVistaDeExito(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Long id = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		
		if(cliente != null) {
		modelo.put("billetera", billetera);
		modelo.put("cliente", cliente);
		modelo.put("saldo", billetera.getSaldo());
		
		return new ModelAndView("confirmacionSaldo", modelo);
		
		}
		
		return new ModelAndView("redirect:/login");
	}
	

	public void setServicioCliente(ServicioCliente servicioClienteMock) {
		this.servicioCliente = servicioClienteMock;
		
	}

	public void setServicioBilletera(ServicioBilletera servicioBilleteraMock) {
		this.servicioBilletera = servicioBilleteraMock;
		
	}
}
