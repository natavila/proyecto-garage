
package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
/*
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobrarTickets;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;
import ar.edu.unlam.tallerweb1.servicios.ServicioQR;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPagarGarage {
	private ServicioEstacionamiento servicioEst;
	private ServicioCobrarTickets servicioCobrarTickets;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	private ServicioGarage servicioGarage;
	private ServicioBilletera servicioBilletera;
	private ServicioQR servQr;
	private ServicioPlan servicioPlan;
	
	@Autowired
	public ControladorPagarGarage(ServicioCobrarTickets servicioCobrarTickets,ServicioAuto servicioAuto,ServicioCliente servicioCliente,ServicioEstacionamiento servicioEst,ServicioGarage servicioGarage, ServicioBilletera servicioBilletera,ServicioQR servQr, ServicioPlan servicioPlan) {
		this.servicioCobrarTickets = servicioCobrarTickets;
		this.servicioAuto = servicioAuto;
		this.servicioCliente = servicioCliente;
		this.servicioEst = servicioEst;
		this.servicioGarage= servicioGarage;
		this.servicioBilletera = servicioBilletera;
		this.servQr = servQr;
		this.servicioPlan = servicioPlan;
	}
	
	@RequestMapping(path="/mostrarFormularioReservaEstadia/{auto.id}/{garage.id}", method=RequestMethod.GET)
	public ModelAndView mostrarFormularioReservaEstadia(@PathVariable("auto.id") Long idAuto,
														@PathVariable("garage.id") Long idGarage,
														HttpServletRequest request) {
		
		String rol = (String) request.getSession().getAttribute("roll");
		Long id = (Long) request.getSession().getAttribute("id");
		ModelMap modelo = new ModelMap();
		Estacionamiento ticket = new Estacionamiento();
		Auto auto = servicioAuto.buscarAuto(idAuto);
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		if(rol != null && rol.equals("cliente"))
			if(cliente != null) {
			if(auto !=null && garage !=null) {
				if(cliente.getPlan() != null) {
					if(cliente.getCantidadAutosRestantes()==0 || cliente.getCantidadHorasRestantes()==0) {
						modelo.put("MensajeError", "Ya no posee mas autos disponibles o se agotaron las horas"); 
					}
				
					
				}
				
				modelo.put("auto", auto);
				modelo.put("cliente", cliente);
				modelo.put("ticket", ticket);
				modelo.put("garage", garage);
				
				return new ModelAndView("formularioReservaEstadia", modelo);
			}
	
			}
		
		return new ModelAndView("redirect:/login");
	}
		

	@RequestMapping(path="/realizarReservaEstadia/{auto.id}/{garage.id}", method=RequestMethod.POST)
	public ModelAndView realizarReservaEstadia(@RequestParam(value="fechaDesde")String fechaDesde,
									@RequestParam(value="fechaHasta")String fechaHasta,
									@PathVariable("auto.id") Long idAuto,
									@PathVariable("garage.id") Long idGarage,
									HttpServletRequest request){
		
		Long id = (Long) request.getSession().getAttribute("id");
		String rol = (String) request.getSession().getAttribute("roll");
		ModelMap modelo = new ModelMap();
		Estacionamiento est = new Estacionamiento();
		Auto auto = servicioAuto.buscarAuto(idAuto);
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		
		if(rol != null && rol.equals("cliente"))
			if(cliente != null) {	
				if(garage !=null && auto!=null && auto.getUsandoGarage().equals(false) && garage.getCapacidad()>garage.getContador()) {
						modelo.put("auto", auto);
						modelo.put("cliente", cliente);
						modelo.put("garage", garage);
						est.setFechaDesde(fechaDesde);
						est.setFechaHasta(fechaHasta);
						est.setGarage1(garage);
						
						servicioAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
					
						
						est.setAuto(auto);
						est.setGarage1(garage);
						est.setActiva(true);
						est.setCliente(cliente);
						
						Long dias = servicioCobrarTickets.calcularDias(est.getFechaDesde(), est.getFechaHasta());
						Double precio = servicioCobrarTickets.calcularPrecioPorEstadia(garage.getPrecioEstadia(), fechaDesde, fechaHasta);
						
						est.setPrecioAPagar(precio);
						
						est.setEstaPagado(false);
						
						modelo.put("ticket", est);
						
						modelo.put("precio", precio);
						
						modelo.put("dias", dias);
						
						servicioCobrarTickets.registrarTicket(est);
						
						if(cliente.getPlan() != null) {
							//servicioPlan.actualizarEstadoPlan(cliente, dias*24);
						}
						
						return new ModelAndView("realizarReservaEstadia", modelo);
					
				}
			
			return new ModelAndView("AlertaAutoEnGarage", modelo);
		
		
			}
		return new ModelAndView("redirect:/login");	
			
}
	
	@RequestMapping(path="/pagarReservaEstadia/{auto.id}/{garage.id}", method=RequestMethod.GET)
	public ModelAndView pagarReservaEstadia(@PathVariable("auto.id") Long idAuto,
											@PathVariable("garage.id") Long idGarage, HttpServletRequest request) throws WriterException, IOException {
		ModelMap modelo = new ModelMap();
		Long idCliente = (Long) request.getSession().getAttribute("id");
		String rol = (String) request.getSession().getAttribute("roll");
		Cliente cliente = servicioCliente.consultarClientePorId(idCliente);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		Auto auto = servicioAuto.buscarAuto(idAuto);
		Estacionamiento estacionamiento = servicioEst.buscarEstacionamientoPorAuto(auto);
				
		if(cliente != null && rol.equals("cliente")) {	
			if(billetera != null && garage != null && auto != null && estacionamiento.getEstaPagado().equals(false)) {
				if(cliente.getPlan() == null) {
					if(billetera.getSaldo() > estacionamiento.getPrecioAPagar()) {
						servicioBilletera.pagarReservaEstadia(estacionamiento, billetera);					
				}else {
					
					modelo.put("cliente", cliente);
					modelo.put("garage", garage);
					modelo.put("estacionamiento", estacionamiento);
					return new ModelAndView("saldoInsuficiente", modelo);
				}
				
			}else {
				servicioPlan.actualizarHorasReservaEstadia(cliente, estacionamiento);
			}
				modelo.put("cliente", cliente);
				modelo.put("garage", garage);
				modelo.put("estacionamiento", estacionamiento);
				Long id = estacionamiento.getId();
				//Devuelve LA IP DEL DUEÑO DE LA PC
				String ip = servQr.devolverIp();
		        //TEXTO DEL QR
				String text = ip+":8080/proyecto-garage/GaragePorQR/"+ idCliente +"/"+ idAuto +"/" + idGarage + "/"+id;
				//GENERA EL QR
				String imagenQr = servQr.generateQR(text).toString();
				
				//Guardo en Un String  la direccion de la Imagen de QR
				servicioEst.meterImagenQr(estacionamiento, imagenQr);
				modelo.put("file", imagenQr);
				return new ModelAndView("confirmacionReservaEstadia", modelo);	
		}
			
	}
		
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path="/mostrarFormularioReservaHora/{auto.id}/{garage.id}", method=RequestMethod.GET)
	public ModelAndView mostrarFormularioReservaHora(
			@PathVariable("auto.id") Long idAuto,
			@PathVariable("garage.id") Long idGarage, 
			HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("roll");
		Long id = (Long) request.getSession().getAttribute("id");
		ModelMap modelo = new ModelMap();
		Estacionamiento ticket = new Estacionamiento();
		Auto auto = servicioAuto.buscarAuto(idAuto);
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		
		if(rol != null && rol.equals("cliente"))
			if(cliente != null) {
				if(auto !=null && garage !=null) {
					if(cliente.getPlan() != null) {
						if(cliente.getCantidadAutosRestantes()==0 || cliente.getCantidadHorasRestantes()==0) {
							modelo.put("MensajeError", "Ya no posee mas autos disponibles o se agotaron las horas"); 
							
						}
					}
					
					modelo.put("auto", auto);
					modelo.put("cliente", cliente);
					modelo.put("ticket", ticket);
					modelo.put("garage", garage);
					
					return new ModelAndView("formularioReservaHora", modelo);
				}
			}
		
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path="/realizarReservaHora/{auto.id}/{garage.id}")
	public ModelAndView realizarReservaHora(@RequestParam(value="horaDesde")String horaDesde,
									@RequestParam(value="horaHasta")String horaHasta,
									@PathVariable("auto.id") Long idAuto,
									@PathVariable("garage.id") Long idGarage,
									HttpServletRequest request
									){
		
		
		String rol = (String) request.getSession().getAttribute("roll");
		Long id = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(id);
		if(rol != null && rol.equals("cliente"))
			if(cliente != null) {
		ModelMap modelo = new ModelMap();
		Estacionamiento est = new Estacionamiento();
		Auto auto = servicioAuto.buscarAuto(idAuto);
		Garage garage = servicioGarage.buscarGarage(idGarage);
			if(garage !=null && auto!=null && auto.getUsandoGarage().equals(false) && servicioGarage.GarageLleno(garage).equals(false) ) {
				modelo.put("auto", auto);
				modelo.put("cliente", cliente);
				modelo.put("garage", garage);
				est.setHoraDesde(horaDesde);
				est.setHoraHasta(horaHasta);
				est.setGarage1(garage);
				
				// METE EL AUTO EN EL GARAGE HACE EL COBRO
				
				servicioAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
				//servicioGarage.sumarContador(garage);
					
				est.setAuto(auto);
				est.setGarage1(garage);
				est.setActiva(true);
				est.setCliente(cliente);
				
				Long horas = servicioCobrarTickets.calcularHoras(est.getHoraDesde(), est.getHoraHasta());
				Double precio = servicioCobrarTickets.calcularPrecioPorHora(garage.getPrecioHora(), horaDesde, horaHasta);
				Long ticket = est.getId();
				est.setPrecioAPagar(precio);
				
				modelo.put("ticket",ticket);
				
				modelo.put("precio", precio);
				
				modelo.put("horas", horas);
				
				servicioCobrarTickets.registrarTicket(est);
				
				
				
				if(cliente.getPlan() != null) {
					//servicioPlan.actualizarEstadoPlan(cliente, horas);
				}
				
				return new ModelAndView("realizarReservaHora", modelo);
			}
			return new ModelAndView("AlertaAutoEnGarage", modelo);
		
		
			}
		return new ModelAndView("redirect:/login");	
			
}
	
	@RequestMapping(path="/pagarReservaPorHora/{auto.id}/{garage.id}", method=RequestMethod.GET)
	public ModelAndView pagarReservaPorHora(@PathVariable("auto.id") Long idAuto,
											@PathVariable("garage.id") Long idGarage,
											
											HttpServletRequest request) throws Exception {
		
		String rol = (String) request.getSession().getAttribute("roll");
		Long idCliente = (Long) request.getSession().getAttribute("id");
		Cliente cliente = servicioCliente.consultarClientePorId(idCliente);
		if(rol != null && rol.equals("cliente"))
			if(cliente != null) {
				ModelMap modelo = new ModelMap();
				Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
				Garage garage = servicioGarage.buscarGarage(idGarage);
				Auto auto = servicioAuto.buscarAuto(idAuto);
				Estacionamiento estacionamiento = servicioEst.buscarEstacionamientoPorAuto(auto);
				
				if(billetera != null && garage != null && auto != null) {
					if(cliente.getPlan() == null) {
						if(billetera.getSaldo() > estacionamiento.getPrecioAPagar()) {
							servicioBilletera.pagarReservaPorHora(estacionamiento, billetera);
						}else {
							modelo.put("cliente", cliente);
							modelo.put("garage", garage);
							modelo.put("estacionamiento", estacionamiento);
							return new ModelAndView("saldoInsuficiente", modelo);
					}
						
					}else {
						
						servicioPlan.actualizarHorasReservaHora(cliente, estacionamiento);
					}
						modelo.put("cliente", cliente);
						modelo.put("garage", garage);
						modelo.put("estacionamiento", estacionamiento);
						
						Long id = estacionamiento.getId();
						//Devuelve LA IP DEL DUEÑO DE LA PC
						String ip = servQr.devolverIp();
				        //TEXTO DEL QR
						String text = ip+":8080/proyecto-garage/GaragePorQR/"+ idCliente +"/"+ idAuto +"/" + idGarage + "/"+id;
						//GENERA EL QR
						String imagenQr = servQr.generateQR(text).toString();
						
						//Guardo en Un String  la direccion de la Imagen de QR
						servicioEst.meterImagenQr(estacionamiento, imagenQr);
		
						return new ModelAndView("confirmacionReservaPorHora", modelo);
					}
						
				}
			

			
					return new ModelAndView("redirect:/login");
			
	}
	
	
	

	@RequestMapping(path="/GaragePorQR/{cliente.id}/{auto.id}/{garage.id}/{id}")
	public ModelAndView entrarAGaragePorQR(
									@PathVariable("cliente.id") Long idCliente,
									@PathVariable("auto.id") Long idAuto,
									@PathVariable("garage.id") Long idGarage,
									@PathVariable("id") Long id			
									){
	
		ModelMap modelo = new ModelMap();
		Auto auto = servicioAuto.buscarAuto(idAuto);
		Cliente cliente = servicioCliente.consultarClientePorId(idCliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		Estacionamiento est = servicioEst.buscarEstacionamiento(id);
	//Document documento = new Document();

	               
			if(garage !=null && auto!=null && auto.getUsandoGarage().equals(true) && servicioGarage.GarageLleno(garage).equals(false) && auto.getReservado().equals(false) ) {
				modelo.put("auto", auto);
				modelo.put("cliente", cliente);
				modelo.put("garage", garage);
				modelo.put("estacionamiento", est);
				//BUSCA EL ID DEL ESTACIONAMIENTO Y LO ACTIVA
				// INGRESA EL AUTO AL GARAGE
				
				servicioGarage.sumarContador(garage);
				//meto la reserva de Estacionamient
				servicioEst.cambiarEstadoDeReserva(est);
				//Meto la reserva de Auto
				servicioAuto.cambiarEstadoReservaAuto(auto);
				
				
				
				
				return new ModelAndView("AgregoPorQR", modelo);
					}
				
			return new ModelAndView("AlertaAutoEnGarage", modelo);
		}
		
	
	
	

	
	public void setServicioBilletera(ServicioBilletera servicioBilletera) {
		this.servicioBilletera = servicioBilletera;
		
	}

	public void setServicioCliente(ServicioCliente servicioCliente) {
		this.servicioCliente = servicioCliente;
		
	}

	public void setServicioGarage(ServicioGarage servicioGarage) {
		this.servicioGarage = servicioGarage;
		
	}

	public void setServicioCobrarTickets(ServicioCobrarTickets servicioCobrarTickets) {
		this.servicioCobrarTickets = servicioCobrarTickets;
		
	}

	public void setServicioEstacionamiento(ServicioEstacionamiento servicioEstacionamiento) {
		this.servicioEst = servicioEstacionamiento;
		
	}

	public void setServicioAuto(ServicioAuto servicioAuto) {
		this.servicioAuto = servicioAuto;
		
	}

	public void setServicioQR(ServicioQR servicioQR) {
		this.servQr = servicioQR;
		
	}
	
	
	
}
