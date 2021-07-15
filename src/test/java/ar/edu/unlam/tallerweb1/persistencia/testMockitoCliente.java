package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import  static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorClientes;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

public class testMockitoCliente extends SpringTest{

	private ServicioLogin servicioLogin;
	private ServicioAuto servicioAuto;
	private ServicioCliente servicioCliente;
	private ServicioBilletera servicioBilletera;
	private ServicioRegistro servicioRegistro;
	private ServicioEstacionamiento servicioEstacionamiento;
	
	private ControladorClientes controladorClientes = new ControladorClientes(servicioLogin, servicioRegistro, servicioAuto, servicioCliente, servicioBilletera, servicioEstacionamiento);

	Cliente clienteMock;
	Auto autoMock;
	Billetera billeteraMock;
	Estacionamiento estacionamientoMock;
	
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioCliente servicioClienteMock;
	private ServicioBilletera servicioBilleteraMock;
	private ServicioEstacionamiento servicioEstacionamientoMock;
	private ServicioAuto servicioAutoMock;
	
	@Before
	public void init(){
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		clienteMock = mock(Cliente.class);
		billeteraMock = mock(Billetera.class);
		autoMock = mock(Auto.class);
		estacionamientoMock = mock(Estacionamiento.class);
		servicioClienteMock = mock(ServicioCliente.class);
		servicioBilleteraMock = mock(ServicioBilletera.class);
		servicioEstacionamientoMock = mock(ServicioEstacionamiento.class);
		servicioAutoMock = mock(ServicioAuto.class);
		controladorClientes.setServicioCliente(servicioClienteMock);
		controladorClientes.setServicioEstacionamiento(servicioEstacionamientoMock);
		controladorClientes.setServicioBilletera(servicioBilleteraMock);
		controladorClientes.setServicioAuto(servicioAutoMock);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queMuestreAutosDeCliente() {
		
		Long idClienteMock = 1L;
		List<Auto> autosDeClienteMock = new ArrayList<>();
		autosDeClienteMock.add(autoMock);

		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioAutoMock.consultarAutoDeClienteActivo(clienteMock)).thenReturn(autosDeClienteMock);
		
		ModelAndView modelAndView = controladorClientes.misAutos(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("ListaAutosDeClienteAgregar");
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queMuestreDatosDeCliente() {
		
		Long idClienteMock = 1L;
		List<Estacionamiento> reservasDeCliente = new ArrayList<>();
		reservasDeCliente.add(estacionamientoMock);
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioBilleteraMock.consultarBilleteraDeCliente(clienteMock)).thenReturn(billeteraMock);
		when(servicioEstacionamientoMock.buscarEstacionamientoPorCliente(clienteMock)).thenReturn(reservasDeCliente);
		
		ModelAndView modelAndView = controladorClientes.mostrarDatosCliente(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("miPerfil");
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queElClientePuedaVerFormularioDeModificarSusDatos() {
		
		Long idClienteMock = 1L;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		
		ModelAndView modelAndView = controladorClientes.modificarCliente(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("modificarCliente");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queSePuedaProcesarLaModificacionDeDatosDelCliente() {
		
		Long idClienteMock = 1L;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		
		ModelAndView modelAndView = controladorClientes.procesarModificarCliente(clienteMock, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/datosCliente");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queSeMuestreLosTicketsDeCliente() {
		
		Long idClienteMock = 1L;
		List<Estacionamiento> reservasDeCliente = new ArrayList<>();
		reservasDeCliente.add(estacionamientoMock);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioBilleteraMock.consultarBilleteraDeCliente(clienteMock)).thenReturn(billeteraMock);
		when(servicioEstacionamientoMock.buscarEstacionamientoPorCliente(clienteMock)).thenReturn(reservasDeCliente);
		
		ModelAndView modelAndView = controladorClientes.mostrarTicketCliente(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("ticketCliente");
	}
}
