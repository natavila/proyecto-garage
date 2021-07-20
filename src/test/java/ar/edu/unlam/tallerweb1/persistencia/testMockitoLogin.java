package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import  static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;

import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

public class testMockitoLogin extends SpringTest{

	private ServicioLogin servicioLoginMock;
	private ServicioEstacionamiento servEstMock;
	private ServicioBilletera servicioBilleteraMock;
	private ServicioGarage servicioGarageMock;
	private ServicioCliente servicioClienteMock;
	private ServicioRegistro servicioRegistroMock;
	private ControladorLogin controladorLogin = new ControladorLogin(servicioLoginMock, servicioRegistroMock, servicioClienteMock, servicioBilleteraMock, servicioGarageMock, servEstMock);
	
	private Cliente clienteMock;
	private Garage garageMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	
	private Billetera billeteraMock;
	
	
	@Before
	public void init() {
		billeteraMock = mock(Billetera.class);
		garageMock = mock(Garage.class);
		clienteMock = mock(Cliente.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock (HttpSession.class);
		
		servicioLoginMock = mock(ServicioLogin.class);
		servicioGarageMock = mock(ServicioGarage.class);
		servicioClienteMock = mock (ServicioCliente.class);
		servicioRegistroMock=mock(ServicioRegistro.class);
		servEstMock= mock(ServicioEstacionamiento.class);
		servicioBilleteraMock = mock(ServicioBilletera.class);
		
		controladorLogin.setServicioGarage(servicioGarageMock);
		controladorLogin.setServicioLogin(servicioLoginMock);
		controladorLogin.setServicioCliente(servicioClienteMock);
		controladorLogin.setServicioRegistro(servicioRegistroMock);
		controladorLogin.setServEst(servEstMock);
		controladorLogin.setServicioBilletera(servicioBilleteraMock);
	}
	
	
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void loginConUsuarioAdminDeberiaIrAHomeAdmin() {
		// lista de Garage
		List<Garage> mockList  = new ArrayList<>();
		Garage garageMock = Mockito.mock(Garage.class);
		mockList.add(garageMock);
		when(servicioGarageMock.consultarGarage()).thenReturn(mockList);
		//Notificaciones De Clientes Nuevos
		when(servicioClienteMock.notificadorDeClientesNuevos()).thenReturn(0);
		//Notificaciones CLientes
		when(servicioRegistroMock.NotificacionesClientes()).thenReturn(0);
		//Dinero Ganado
		when(servEstMock.dineroGanadoEnTotal()).thenReturn(0.0);
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(servicioLoginMock.consultarCliente(clienteMock)).thenReturn(clienteMock);
		when(clienteMock.getRoll()).thenReturn("admin");
		
		
		
		
		ModelAndView vista = controladorLogin.validarLogin(clienteMock, requestMock);
		assertThat(vista.getViewName()).isEqualTo("homeAdmin");
		
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void loginConUsuarioAdminDeberiaIrAHomeCliente() {
		
		
		List<Garage> mockList  = new ArrayList<>();
		Garage garageMock = Mockito.mock(Garage.class);
		mockList.add(garageMock);
		when(servicioGarageMock.consultarGarage()).thenReturn(mockList);
		//Notificaciones De Clientes Nuevos
		when(servicioClienteMock.notificadorDeClientesNuevos()).thenReturn(0);
		//Notificaciones CLientes
		when(servicioRegistroMock.NotificacionesClientes()).thenReturn(0);
		//Dinero Ganado
		when(servEstMock.dineroGanadoEnTotal()).thenReturn(0.0);
		//Billetera
		when(servicioBilleteraMock.consultarBilleteraDeCliente(clienteMock)).thenReturn(billeteraMock);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(servicioLoginMock.consultarCliente(clienteMock)).thenReturn(clienteMock);
		when(clienteMock.getRoll()).thenReturn("cliente");
		
	
		ModelAndView vista = controladorLogin.validarLogin(clienteMock, requestMock);
		assertThat(vista.getViewName()).isEqualTo("home");
		
	}
	
	
	
	
	
	
	
	
	
}
