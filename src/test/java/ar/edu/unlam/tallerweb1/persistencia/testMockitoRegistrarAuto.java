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
import ar.edu.unlam.tallerweb1.controladores.ControladorRegistroAuto;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;

public class testMockitoRegistrarAuto extends SpringTest{

	private ServicioCliente servicioCliente;	
	private ServicioAuto servicioAuto;
	private ServicioPlan servicioPlan;
	
	private ControladorRegistroAuto controladorRegistroAuto = new ControladorRegistroAuto(servicioCliente, servicioAuto, servicioPlan);
	
	Cliente clienteMock;
	Auto autoMock;
	
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioCliente servicioClienteMock;
	private ServicioAuto servicioAutoMock;
	private ServicioPlan servicioPlanMock;
	
	@Before
	public void init() {
		clienteMock = mock(Cliente.class);
		autoMock = mock(Auto.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioClienteMock = mock(ServicioCliente.class);
		servicioAutoMock = mock(ServicioAuto.class);
		servicioPlanMock = mock(ServicioPlan.class);
		controladorRegistroAuto.setServicioCliente(servicioClienteMock);
		controladorRegistroAuto.setServicioAuto(servicioAutoMock);
		controladorRegistroAuto.setServicioPlan(servicioPlanMock);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queMuestreElRegistroDeAuto() {
		
		Long idClienteMock = 1L;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		
		ModelAndView modelAndView = controladorRegistroAuto.registro(requestMock); 
		
		assertThat(modelAndView.getViewName()).isEqualTo("registroAuto");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void queProceseElRegistroDelAuto() {
	
		Long idClienteMock = 1L;
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioClienteMock.consultarClientePorId(idClienteMock)).thenReturn(clienteMock);
		when(servicioAutoMock.consultarAuto(autoMock)).thenReturn(null);
		
		ModelAndView modelAndView = controladorRegistroAuto.procesarRegistroAuto(autoMock, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/misAutos");
		
	}
	
}
