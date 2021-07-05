
package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

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

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.sun.tools.javac.Main;

import ar.edu.unlam.tallerweb1.modelo.Garage;
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
import ar.edu.unlam.tallerweb1.servicios.ServicioQR;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;


@Controller
public class ControladorPagarGarage {
	private ServicioEstacionamiento servicioEst;
	private ServicioCobrarTickets servicioCobrarTickets;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	private ServicioGarage servicioGarage;
	private ServicioBilletera servicioBilletera;
	private ServicioQR servQr;
	@Autowired
	public ControladorPagarGarage(ServicioCobrarTickets servicioCobrarTickets,ServicioAuto servicioAuto,ServicioCliente servicioCliente,ServicioEstacionamiento servicioEst,ServicioGarage servicioGarage, ServicioBilletera servicioBilletera,ServicioQR servQr) {
		this.servicioCobrarTickets = servicioCobrarTickets;
		this.servicioAuto = servicioAuto;
		this.servicioCliente = servicioCliente;
		this.servicioEst = servicioEst;
		this.servicioGarage= servicioGarage;
		this.servicioBilletera = servicioBilletera;
		this.servQr = servQr;
	}
	
	@RequestMapping(path="/mostrarFormularioReservaEstadia/{cliente.id}/{auto.id}/{garage.id}", method=RequestMethod.GET)
	public ModelAndView mostrarFormularioReservaEstadia(@PathVariable("cliente.id") Long idCliente,
														@PathVariable("auto.id") Long idAuto,
														@PathVariable("garage.id") Long idGarage,
														HttpServletRequest request) {
		
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
		
		
		ModelMap modelo = new ModelMap();
		Estacionamiento ticket = new Estacionamiento();
		Auto auto1 = servicioAuto.buscarAuto(idAuto);
		Cliente cliente1 = servicioCliente.consultarClientePorId(idCliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		
			if(auto1 !=null && cliente1 !=null && garage !=null) {
				
				modelo.put("auto", auto1);
				modelo.put("cliente", cliente1);
				modelo.put("ticket", ticket);
				modelo.put("garage", garage);
				
				return new ModelAndView("formularioReservaEstadia", modelo);
			}
	
		return new ModelAndView("formularioReservaEstadia", modelo);
			}
		return new ModelAndView("redirect:/login");
	}
		

	@RequestMapping(path="/realizarReservaEstadia/{cliente.id}/{auto.id}/{garage.id}", method=RequestMethod.POST)
	public ModelAndView procesarPagoEstadia(@RequestParam(value="fechaDesde")String fechaDesde,
									@RequestParam(value="fechaHasta")String fechaHasta,
									@PathVariable("cliente.id") Long idCliente,
									@PathVariable("auto.id") Long idAuto,
									@PathVariable("garage.id") Long idGarage,
									HttpServletRequest request){
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
		ModelMap modelo = new ModelMap();
		Estacionamiento est = new Estacionamiento();
		Auto auto = servicioAuto.buscarAuto(idAuto);
		Cliente cliente = servicioCliente.consultarClientePorId(idCliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		                                       //Esto le puse Nuevo
			if(garage !=null && auto!=null && auto.getUsandoGarage().equals(false) && garage.getCapacidad()>garage.getContador()) {
				modelo.put("auto", auto);
				modelo.put("cliente", cliente);
				modelo.put("garage", garage);
				est.setFechaDesde(fechaDesde);
				est.setFechaHasta(fechaHasta);
				est.setGarage1(garage);
				
				servicioAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
				//servicioGarage.sumarContador(garage);
				//auto.setUsandoGarage(true);
				
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
				
				return new ModelAndView("pagarMontoEstadia", modelo);
			}
			return new ModelAndView("AlertaAutoEnGarage", modelo);
		
		
			}
		return new ModelAndView("redirect:/login");	
			
}
	
	@RequestMapping(path="/pagarReservaEstadia/{cliente.id}/{auto.id}/{garage.id}", method=RequestMethod.GET)
	public ModelAndView pagarReservaEstadia(@PathVariable("cliente.id") Long idCliente,
											@PathVariable("auto.id") Long idAuto,
											@PathVariable("garage.id") Long idGarage) throws WriterException, IOException {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(idCliente);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		Auto auto = servicioAuto.buscarAuto(idAuto);
		Estacionamiento estacionamiento = servicioEst.buscarEstacionamientoPorAuto(auto);
		
		
			
			if(billetera != null && garage != null && auto != null && estacionamiento.getEstaPagado().equals(false)) {
				if(billetera.getSaldo() > estacionamiento.getPrecioAPagar()) {
					servicioBilletera.pagarReservaEstadia(estacionamiento, billetera);
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
				}else {
					modelo.put("cliente", cliente);
					modelo.put("garage", garage);
					modelo.put("estacionamiento", estacionamiento);
					return new ModelAndView("saldoInsuficiente", modelo);
				}
				
			}
		
		return new ModelAndView("realizarReservaEstadia/{cliente.id}/{auto.id}/{garage.id}");
	}
	
	@RequestMapping(path="/mostrarFormularioReservaHora/{cliente.id}/{auto.id}/{garage.id}", method=RequestMethod.GET)
	public ModelAndView mostrarFormularioReservaHora(
			@PathVariable("cliente.id") Long idCliente,
			@PathVariable("auto.id") Long idAuto,
			@PathVariable("garage.id") Long idGarage, 
			HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
		
		
		ModelMap modelo = new ModelMap();
		Estacionamiento ticket = new Estacionamiento();
		Auto auto1 = servicioAuto.buscarAuto(idAuto);
		Cliente cliente1 = servicioCliente.consultarClientePorId(idCliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		
			if(auto1 !=null && cliente1 !=null && garage !=null) {
				
				modelo.put("auto", auto1);
				modelo.put("cliente", cliente1);
				modelo.put("ticket", ticket);
				modelo.put("garage", garage);
				
				return new ModelAndView("formularioReservaHora", modelo);
			}
	
		return new ModelAndView("formularioReservaHora", modelo);
			}
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(path="/realizarReservaHora/{cliente.id}/{auto.id}/{garage.id}")
	public ModelAndView procesarPagoHora(@RequestParam(value="horaDesde")String horaDesde,
									@RequestParam(value="horaHasta")String horaHasta,
									@PathVariable("cliente.id") Long idCliente,
									@PathVariable("auto.id") Long idAuto,
									@PathVariable("garage.id") Long idGarage,
									HttpServletRequest request
									) throws WriterException, IOException{
		
		
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
		ModelMap modelo = new ModelMap();
		Estacionamiento est = new Estacionamiento();
		Auto auto = servicioAuto.buscarAuto(idAuto);
		Cliente cliente = servicioCliente.consultarClientePorId(idCliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		                                       //Esto le puse Nuevo
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
				
				return new ModelAndView("pagarMontoHora", modelo);
			}
			return new ModelAndView("AlertaAutoEnGarage", modelo);
		
		
			}
		return new ModelAndView("redirect:/login");	
			
}
	
	@RequestMapping(path="/pagarReservaPorHora/{cliente.id}/{auto.id}/{garage.id}", method=RequestMethod.GET)
	public ModelAndView pagarReservaPorHora(@PathVariable("cliente.id") Long idCliente,
											@PathVariable("auto.id") Long idAuto,
											@PathVariable("garage.id") Long idGarage,
											
											HttpServletRequest request) throws Exception {
		
		String rol = (String) request.getSession().getAttribute("roll");
		if(rol != null)
			if(rol.equals("cliente")) {
		ModelMap modelo = new ModelMap();
		Cliente cliente = servicioCliente.consultarClientePorId(idCliente);
		Billetera billetera = servicioBilletera.consultarBilleteraDeCliente(cliente);
		Garage garage = servicioGarage.buscarGarage(idGarage);
		Auto auto = servicioAuto.buscarAuto(idAuto);
		
		Estacionamiento estacionamiento = servicioEst.buscarEstacionamientoPorAuto(auto);
		

			if(billetera != null && garage != null && auto != null) {
				if(billetera.getSaldo() > estacionamiento.getPrecioAPagar()) {
					servicioBilletera.pagarReservaPorHora(estacionamiento, billetera);
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
					
					
					return new ModelAndView("confirmacionReservaPorHora", modelo);
				}else {
					return new ModelAndView("saldoInsuficiente", modelo);
				}
				
			}
		
		return new ModelAndView("realizarReservaEstadia/{cliente.id}/{auto.id}/{garage.id}");
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
	               
			if(garage !=null && auto!=null && auto.getUsandoGarage().equals(true) && servicioGarage.GarageLleno(garage).equals(false) && auto.getReservado().equals(false) ) {
				modelo.put("auto", auto);
				modelo.put("cliente", cliente);
				modelo.put("garage", garage);
				modelo.put("estacionamiento", est);
				//BUSCA EL ID DEL ESTACIONAMIENTO Y LO ACTIVA
				// INGRESA EL AUTO AL GARAGE
				
				/*
				servicioAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
				
				servicioEst.ActivarQR(id);
				
				*/
				servicioGarage.sumarContador(garage);
				//meto la reserva de Estacionamient
				servicioEst.cambiarEstadoDeReserva(est);
				//Meto la reserva de Auto
				servicioAuto.cambiarEstadoReservaAuto(auto);
				
				return new ModelAndView("AgregoPorQR", modelo);
					}
				
			return new ModelAndView("AlertaAutoEnGarage", modelo);
		}
		
	
			
	@RequestMapping(path="/generarPdf", method=RequestMethod.GET)
	public void generarPdf() throws Exception{
		
	}
	
	
	
}
