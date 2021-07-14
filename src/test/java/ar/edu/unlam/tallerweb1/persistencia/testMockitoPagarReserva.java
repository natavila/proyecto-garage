package ar.edu.unlam.tallerweb1.persistencia;

import  static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.WriterException;
import java.util.Random;
import java.util.UUID;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.controladores.ControladorPagarGarage;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobrarTickets;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioQR;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

public class testMockitoPagarReserva extends SpringTest{

	private ServicioEstacionamiento servicioEst;
	private ServicioCobrarTickets servicioCobrarTickets;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	private ServicioGarage servicioGarage;
	private ServicioBilletera servicioBilletera;
	private ServicioQR servQr;
	
	private ControladorPagarGarage controladorPagarGarage = new ControladorPagarGarage(servicioCobrarTickets, servicioAuto, servicioCliente, servicioEst, servicioGarage, servicioBilletera, servQr);
	Cliente clienteMock;
	Billetera billeteraMock;
	Auto autoMock;
	Garage garageMock;
	Estacionamiento ticketMock;
	
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioCliente servicioClienteMock;
	private ServicioBilletera servicioBilleteraMock;
	private ServicioGarage servicioGarageMock;
	private ServicioCobrarTickets servicioCobrarTicketsMock;
	private ServicioEstacionamiento servicioEstacionamientoMock;
	private ServicioAuto servicioAutoMock;
	private ServicioQR servQrMock;
	
	@Before
	public void init(){
		clienteMock = mock(Cliente.class);
		garageMock =mock(Garage.class);
		billeteraMock = mock(Billetera.class);
		autoMock = mock(Auto.class);
		ticketMock = mock(Estacionamiento.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioClienteMock = mock(ServicioCliente.class);
		servicioBilleteraMock = mock(ServicioBilletera.class);
		servicioGarageMock = mock(ServicioGarage.class);
		servicioAutoMock = mock(ServicioAuto.class);
		servicioCobrarTicketsMock = mock(ServicioCobrarTickets.class);
		servicioEstacionamientoMock = mock(ServicioEstacionamiento.class);
		servQrMock = mock(ServicioQR.class);
		controladorPagarGarage.setServicioBilletera(servicioBilleteraMock);
		controladorPagarGarage.setServicioCliente(servicioClienteMock);
		controladorPagarGarage.setServicioGarage(servicioGarageMock);
		controladorPagarGarage.setServicioCobrarTickets(servicioCobrarTicketsMock);
		controladorPagarGarage.setServicioEstacionamiento(servicioEstacionamientoMock);
		controladorPagarGarage.setServicioAuto(servicioAutoMock);
		controladorPagarGarage.setServicioQR(servQrMock);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void clientePuedaVerFormularioDeReservaEstadia(){
		Long idClienteMock = 1L;
		Long idAutoMock = 1L;
		Long idGarageMock = 1L;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(servicioAutoMock.buscarAuto(idAutoMock)).thenReturn(autoMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioGarageMock.buscarGarage(idGarageMock)).thenReturn(garageMock);	
		
		ModelAndView modelAndView = controladorPagarGarage.mostrarFormularioReservaEstadia(idClienteMock, idAutoMock, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("formularioReservaEstadia");
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void clientePuedaRealizarReservaEstadia() {
		Long idClienteMock = 1L;
		Long idAutoMock = 1L;
		Long idGarageMock = 1L;
		String fechaDesde = "2021-07-07";
		String fechaHasta = "2021-07-08";
		Long diasEnGarage = servicioCobrarTicketsMock.calcularDias(fechaDesde, fechaHasta);
		Double precioEstadia = 200.0;
		Double total = servicioCobrarTicketsMock.calcularPrecioPorEstadia(precioEstadia, fechaDesde, fechaHasta);
		garageMock.setCapacidad(10);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(autoMock.getUsandoGarage()).thenReturn(false);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioGarageMock.buscarGarage(idGarageMock)).thenReturn(garageMock);
		when(servicioAutoMock.buscarAuto(idAutoMock)).thenReturn(autoMock);
		when(autoMock.getUsandoGarage()).thenReturn(false);
		when(garageMock.getCapacidad()).thenReturn(4);
		when(servicioCobrarTicketsMock.calcularDias(fechaDesde, fechaHasta)).thenReturn(diasEnGarage);
		when(servicioCobrarTicketsMock.calcularPrecioPorEstadia(precioEstadia, fechaDesde, fechaHasta)).thenReturn(total);
		
		ModelAndView modelAndView = controladorPagarGarage.procesarPagoEstadia(fechaDesde, fechaHasta, idClienteMock, idAutoMock, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("pagarMontoEstadia");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void clientePuedaPagarReservaEstadia() throws WriterException, IOException {
		
		Long idClienteMock = 1L;
		Long idAutoMock = 1L;
		Long idGarageMock = 1L;
		File fileMock = mock(File.class);
		Double total = 500.0;
		String ip = servQrMock.devolverIp();
		billeteraMock.setSaldo(10000.0);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioGarageMock.buscarGarage(idGarageMock)).thenReturn(garageMock);
		when(servicioAutoMock.buscarAuto(idAutoMock)).thenReturn(autoMock);
		when(servicioEstacionamientoMock.buscarEstacionamientoPorAuto(autoMock)).thenReturn(ticketMock);
		when(servicioBilleteraMock.consultarBilleteraDeCliente(clienteMock)).thenReturn(billeteraMock);
		when(ticketMock.getEstaPagado()).thenReturn(false);
		when(billeteraMock.getSaldo()).thenReturn(total);
		when(servQrMock.devolverIp()).thenReturn(ip);
		when(servQrMock.generateQR(anyString())).thenReturn(fileMock);
		
		ModelAndView modelAndView = controladorPagarGarage.pagarReservaEstadia(idClienteMock, idAutoMock, idGarageMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("confirmacionReservaEstadia");
		
	}
	
	
}
