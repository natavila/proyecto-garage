import ar.edu.unlam.tallerweb1.SpringTest;

import ar.edu.unlam.tallerweb1.controladores.ControladorGarage;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLocalidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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

public class testControladorGarage extends SpringTest {

	private ServicioGarage servicioGarageMock;
	private ServicioEstacionamiento servicioEstMock;
	private ServicioLogin servicioLoginMock;
	private ServicioRegistro servicioRegistroMock;
	private ServicioAuto servicioAutoMock;
	private ServicioCliente servicioClienteMock;
	private ServicioLocalidad servicioLocMock;
	private ControladorGarage controladorGarage = new ControladorGarage(servicioGarageMock, servicioRegistroMock,
			servicioAutoMock, servicioClienteMock, servicioLocMock, servicioLoginMock, servicioEstMock);
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private Garage garageMock;
	private Auto autoMock;
	private Cliente clienteMock;

	@Before

	public void init() {
		garageMock = mock(Garage.class);
		autoMock = mock(Auto.class);
		clienteMock = mock(Cliente.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);

		servicioGarageMock = mock(ServicioGarage.class);
		servicioEstMock = mock(ServicioEstacionamiento.class);
		servicioLoginMock = mock(ServicioLogin.class);
		servicioRegistroMock = mock(ServicioRegistro.class);
		servicioAutoMock = mock(ServicioAuto.class);
		servicioClienteMock = mock(ServicioCliente.class);
		servicioLocMock = mock(ServicioLocalidad.class);

		controladorGarage.setServicioGarage(servicioGarageMock);
		controladorGarage.setServicioEst(servicioEstMock);
		controladorGarage.setServicioLogin(servicioLoginMock);
		controladorGarage.setServicioRegistro(servicioRegistroMock);
		controladorGarage.setServicioAuto(servicioAutoMock);
		controladorGarage.setServicioCliente(servicioClienteMock);
		controladorGarage.setServicioLoc(servicioLocMock);

	}

	@Test
	@Rollback(true)
	@Transactional
	public void loginAdminLlevaAVistaListaHistoricaDeAutosEnGarage() {
		Long idGarage = 5L;
		List<Auto> listaMock = new ArrayList<>();
		listaMock.add(autoMock);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("roll")).thenReturn("admin");
		
		when(servicioGarageMock.buscarGarage(idGarage)).thenReturn(garageMock);
		when(servicioEstMock.buscarAutosDeUnGarage(garageMock)).thenReturn(listaMock);
		
		ModelAndView vista = controladorGarage.MostrarHistoricoDeGarage(idGarage, requestMock);
		assertThat(vista.getViewName()).isEqualTo("ListaHistoricaDeAutosEnGarage");
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void loginClienteLlevaAVistaLogin() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("roll")).thenReturn("cliente");
		
		ModelAndView vista = controladorGarage.MostrarHistoricoDeGarage(5L, requestMock);
		assertThat(vista.getViewName()).isEqualTo("redirect:/login");
	}


	
}
