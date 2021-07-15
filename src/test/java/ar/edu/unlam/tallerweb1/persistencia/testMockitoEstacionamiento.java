package ar.edu.unlam.tallerweb1.persistencia;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import  static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.servlet.ModelAndView;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorEstacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;

public class testMockitoEstacionamiento  extends SpringTest{

	private ServicioAuto servicioAutoMock;
	private ServicioGarage servicioGarageMock;
	private ServicioEstacionamiento servicioEstacionamientoMock;
	private ServicioCliente servicioClienteMock;
	
	private ControladorEstacionamiento controladorEstacionamiento = new ControladorEstacionamiento(servicioClienteMock, servicioGarageMock, servicioAutoMock, servicioEstacionamientoMock);
	
	private Cliente clienteMock;
	private Auto autoMock;
	private Estacionamiento estacionamientoMock;
	private Garage garageMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	
	
	@Before
	public void init() {
	clienteMock = mock(Cliente.class);
	autoMock = mock(Auto.class);
	estacionamientoMock = mock(Estacionamiento.class);
	garageMock = mock(Garage.class);
	requestMock = mock(HttpServletRequest.class);
	sessionMock = mock (HttpSession.class);
	
	servicioGarageMock = mock(ServicioGarage.class);
	servicioEstacionamientoMock = mock(ServicioEstacionamiento.class);
	servicioAutoMock = mock(ServicioAuto.class);
	controladorEstacionamiento.setServicioAuto(servicioAutoMock);
	controladorEstacionamiento.setServicioEst(servicioEstacionamientoMock);
	controladorEstacionamiento.setServicioGarage(servicioGarageMock);
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void irAVistaSacarAutoDeGarage() {
		
		List<Auto> mockList  = new ArrayList<>();
		Auto autoMock = Mockito.mock(Auto.class);
		mockList.add(autoMock);

		when(requestMock.getSession()).thenReturn(sessionMock);
		when(clienteMock.getRoll()).thenReturn("admin");
		when(servicioGarageMock.buscarGarage(anyLong())).thenReturn(garageMock);
		
		when(servicioEstacionamientoMock.buscarAutosQueEstenActivosEnUnGarage(garageMock)).thenReturn((ArrayList<Auto>) mockList);
		
		when(servicioEstacionamientoMock.buscarEstacionamiento(anyLong())).thenReturn(estacionamientoMock);
		
		ModelAndView vista = controladorEstacionamiento.SacarAutoDeGaragePorTicket((long) 5, (long) 5, requestMock);
		assertThat(vista.getViewName()).isEqualTo("confirmacionSacarTicket");		
			
	}

	
}
