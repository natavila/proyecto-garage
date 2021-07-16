package ar.edu.unlam.tallerweb1.persistencia;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorAdmin;
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

public class testMockitoAdmin extends SpringTest {

	private ServicioGarage servicioGarageMock;
	private ServicioEstacionamiento servicioEstMock;
	private ServicioLogin servicioLoginMock;
	private ServicioRegistro servicioRegistroMock;
	private ServicioAuto servicioAutoMock;
	private ServicioCliente servicioClienteMock;
	private ServicioLocalidad servicioLocMock;
	private ControladorAdmin controladorAdmin= new ControladorAdmin(servicioRegistroMock, servicioLocMock, servicioEstMock, servicioGarageMock, servicioClienteMock, null, servicioLoginMock, servicioAutoMock);
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

		controladorAdmin.setServicioGarage(servicioGarageMock);
		controladorAdmin.setServicioEst(servicioEstMock);
		controladorAdmin.setServicioLogin(servicioLoginMock);
		controladorAdmin.setServicioRegistro(servicioRegistroMock);
		controladorAdmin.setServicioAuto(servicioAutoMock);
		controladorAdmin.setServicioCliente(servicioClienteMock);
		controladorAdmin.setServicioLoc(servicioLocMock);

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
		
		ModelAndView vista = controladorAdmin.MostrarHistoricoDeGarage(idGarage, requestMock);
		assertThat(vista.getViewName()).isEqualTo("ListaHistoricaDeAutosEnGarage");
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void loginClienteLlevaAVistaLogin() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("roll")).thenReturn("cliente");
		
		ModelAndView vista = controladorAdmin.MostrarHistoricoDeGarage(5L, requestMock);
		assertThat(vista.getViewName()).isEqualTo("redirect:/login");
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void irAVistaAgregarGarage() {
		List<String> listaMock = new ArrayList<>();
		listaMock.add("Laferrere");
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("roll")).thenReturn("admin");
		
		when(servicioLocMock.devolverNombresDeLocalidades()).thenReturn(listaMock);
		
		ModelAndView vista = controladorAdmin.mostrarFormularioGaraga(requestMock);
		assertThat(vista.getViewName()).isEqualTo("agregarGarage");
		
	}
	@Test
	@Rollback(true)
	@Transactional
	public void irAVistaDatosGaragePorPantalla() {
		
		List<Garage> listaMock = new ArrayList<>();
		listaMock.add(garageMock);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("roll")).thenReturn("admin");
		
		when(servicioGarageMock.consultarGarage()).thenReturn(listaMock);
		ModelAndView vista = controladorAdmin.Listar(requestMock);
		assertThat(vista.getViewName()).isEqualTo("DatosGaragesPorPantalla");
		
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void irAVistaDatosGaragePorPantallaOrdenadoPorHora() {
		List<Garage> listaMock = new ArrayList<>();
		listaMock.add(garageMock);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("roll")).thenReturn("admin");
		when(servicioGarageMock.ordenarGaragePorHora()).thenReturn((ArrayList<Garage>) listaMock);
		
		ModelAndView vista = controladorAdmin.Listar(requestMock);
		assertThat(vista.getViewName()).isEqualTo("DatosGaragesPorPantalla");
		
		
	}
	@Test
	@Rollback(true)
	@Transactional
	public void irAVistaDatosGaragePorPantallaOrdenadoPorEstadia() {
		List<Garage> listaMock = new ArrayList<>();
		listaMock.add(garageMock);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("roll")).thenReturn("admin");
		when(servicioGarageMock.ordenarGaragePorEstadia()).thenReturn((ArrayList<Garage>) listaMock);
		
		ModelAndView vista = controladorAdmin.Listar(requestMock);
		assertThat(vista.getViewName()).isEqualTo("DatosGaragesPorPantalla");
		
		
	}
	
	


	
}
