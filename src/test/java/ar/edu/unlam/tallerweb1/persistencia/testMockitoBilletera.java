package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import  static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorBilletera;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;

public class testMockitoBilletera extends SpringTest{

	private ServicioBilletera servicioBilletera;
	private ServicioCliente servicioCliente;
	
	private ControladorBilletera controladorBilletera = new ControladorBilletera(servicioBilletera, servicioCliente);
	
	Cliente clienteMock;
	Billetera billeteraMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioCliente servicioClienteMock;
	private ServicioBilletera servicioBilleteraMock;
	
	@Before
	public void init() {
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		clienteMock = mock(Cliente.class);
		billeteraMock = mock(Billetera.class);
		servicioClienteMock = mock(ServicioCliente.class);
		servicioBilleteraMock = mock(ServicioBilletera.class);
		controladorBilletera.setServicioCliente(servicioClienteMock);
		controladorBilletera.setServicioBilletera(servicioBilleteraMock);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queDevuelvaElRegistroDeLaBilletera() {
		
		Long idClienteMock = 1L;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		
		ModelAndView modelAndView = controladorBilletera.registro(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("registroBilletera");
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queProceseElRegistroDeLaBilletera() {
		
		Long idClienteMock = 1L;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioBilleteraMock.consultarBilleteraDeCliente(clienteMock)).thenReturn(null);
		
		ModelAndView modelAndView = controladorBilletera.procesarRegistro(billeteraMock, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("confirmacionBilletera");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queSePuedaMostraLaBilletera() {
		
		Long idClienteMock = 1L;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioBilleteraMock.consultarBilleteraDeCliente(clienteMock)).thenReturn(billeteraMock);
		
		ModelAndView modelAndView = controladorBilletera.mostrarBilletera(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("miBilletera");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queElClientePuedaIngresarDineroASuBilletera() {
		
		Long idClienteMock = 1L;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioBilleteraMock.consultarBilleteraDeCliente(clienteMock)).thenReturn(billeteraMock);
		
		ModelAndView modelAndView = controladorBilletera.formularioSaldo(billeteraMock, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("ingresarSaldo");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queSePuedaProcesarElDineroIngresadoALaBilletera() {
		
		Long idClienteMock = 1L;
		Double monto = 200.0;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioBilleteraMock.consultarBilleteraDeCliente(clienteMock)).thenReturn(billeteraMock);
		
		ModelAndView modelAndView = controladorBilletera.ingresarSaldo(billeteraMock, monto, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/dineroCargadoExitosamente");
		
	}
}
