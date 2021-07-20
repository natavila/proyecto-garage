package ar.edu.unlam.tallerweb1.persistencia;
import  static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

public class testMockitoLoginCliente extends SpringTest{

	private ServicioLogin servicioLogin;
	private ServicioEstacionamiento servEst;
	private ServicioBilletera servicioBilletera;
	private ServicioGarage servicioGarage;
	private ServicioCliente servicioCliente;
	private ServicioRegistro servicioRegistro;
	private ServicioAuto servicioAuto;
	
	private ControladorLogin controladorLogin = new ControladorLogin(servicioLogin, servicioRegistro, servicioCliente, servicioBilletera, servicioGarage, servEst, servicioAuto);
	private Cliente usuarioMock;
	private Billetera billeteraMock;
	private Garage garageMock;
	
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioLogin servicioLoginMock;
	private ServicioBilletera servicioBilleteraMock;
	private ServicioGarage servicioGarageMock;
	
	@Before
	public void init(){
		usuarioMock = mock(Cliente.class);
		garageMock =mock(Garage.class);
		billeteraMock = mock(Billetera.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioLoginMock = mock(ServicioLogin.class);
		servicioBilleteraMock = mock(ServicioBilletera.class);
		servicioGarageMock = mock(ServicioGarage.class);
		controladorLogin.setServicioLogin(servicioLoginMock);
		controladorLogin.setServicioBilletera(servicioBilleteraMock);
		controladorLogin.setServicioGarage(servicioGarageMock);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void loginConUsuarioExistenteDeberiaLLevarASuHomeCorrespondiente(){
		
		Long idClienteMock = 1L;
		List<Garage> listaMock  = new ArrayList<>();
		listaMock.add(garageMock);
		List<Garage> listaGaragesCercanos  = new ArrayList<>();
		listaGaragesCercanos.add(garageMock);
		sessionMock.setAttribute("id", idClienteMock);
		
		// preparacion
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(servicioLoginMock.consultarCliente(usuarioMock)).thenReturn(usuarioMock);
		when(servicioBilleteraMock.consultarBilleteraDeCliente(usuarioMock)).thenReturn(billeteraMock);
		when(servicioGarageMock.consultarGarage()).thenReturn(listaMock);
		when(servicioGarageMock.buscarGarageQueCoincidanConLocalidadDeCliente(usuarioMock)).thenReturn(listaGaragesCercanos);
		when(usuarioMock.getRoll()).thenReturn("cliente");

		// ejecucion
		ModelAndView modelAndView = controladorLogin.validarLogin(usuarioMock, requestMock);
		
		// validacion
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/home");
		//verify(sessionMock, times(1)).setAttribute("roll", "cliente");
	}

}
