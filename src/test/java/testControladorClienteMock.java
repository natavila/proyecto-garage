import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorClientes;
import ar.edu.unlam.tallerweb1.controladores.ControladorGarage;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioAuto;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLocalidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

public class testControladorClienteMock extends SpringTest{
	private ServicioGarage servicioGarageMock;
	private ServicioBilletera servicioBilleteraMock;
	private ServicioEstacionamiento servicioEstMock;
	private ServicioLogin servicioLoginMock;
	private ServicioRegistro servicioRegistroMock;
	private ServicioAuto servicioAutoMock;
	private ServicioCliente servicioClienteMock;
	private ServicioLocalidad servicioLocMock;
	private ControladorClientes controladorClientesMock = new ControladorClientes(servicioLoginMock, servicioRegistroMock, servicioAutoMock, servicioClienteMock, servicioBilleteraMock, servicioEstMock);
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private Garage garageMock;
	private Auto autoMock;
	private Cliente clienteMock;
	private Model modelMock;
	private Billetera billeteraMock;
	private Estacionamiento estMock;

	@Before

	public void init() {
		garageMock = mock(Garage.class);
		autoMock = mock(Auto.class);
		clienteMock = mock(Cliente.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		modelMock = mock(Model.class);
		billeteraMock = mock(Billetera.class);
		estMock = mock(Estacionamiento.class);
		servicioGarageMock = mock(ServicioGarage.class);
		servicioEstMock = mock(ServicioEstacionamiento.class);
		servicioLoginMock = mock(ServicioLogin.class);
		servicioRegistroMock = mock(ServicioRegistro.class);
		servicioAutoMock = mock(ServicioAuto.class);
		servicioClienteMock = mock(ServicioCliente.class);
		servicioLocMock = mock(ServicioLocalidad.class);
		servicioBilleteraMock = mock(ServicioBilletera.class);
		controladorClientesMock.setServicioBilletera(servicioBilleteraMock);
		controladorClientesMock.setServicioLogin(servicioLoginMock);
		controladorClientesMock.setServicioRegistro(servicioRegistroMock);
		controladorClientesMock.setServicioAuto(servicioAutoMock);
		controladorClientesMock.setServicioCliente(servicioClienteMock);
		controladorClientesMock.setServicioEstacionamiento(servicioEstMock);

	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void testQueMuestraListaDeClientes() {
		
		List<Cliente> listaMock = new ArrayList<>();
		listaMock.add(clienteMock);
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("roll")).thenReturn("admin");
		when(servicioLoginMock.listaDeClientes()).thenReturn(listaMock);
		when(servicioRegistroMock.NotificacionesClientes()).thenReturn(2);
		ModelAndView vista = (controladorClientesMock.clientes(modelMock, requestMock));
		assertThat(vista.getViewName()).isEqualTo("ListaClientes");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testQueMuestraListaDeAutosDeCliente() {
		List<Auto> listaMock = new ArrayList<>();
		listaMock.add(autoMock);
		Long idClienteMock = 5L;
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);
		when(requestMock.getSession().getAttribute("roll")).thenReturn("cliente");
		when(servicioClienteMock.consultarClientePorId((long) 5)).thenReturn(clienteMock);
		
		
		
		when(servicioAutoMock.consultarAutoDeClienteActivo(clienteMock)).thenReturn(listaMock);
		
		ModelAndView vista = (controladorClientesMock.misAutos(requestMock));
		assertThat(vista.getViewName()).isEqualTo("ListaAutosDeClienteAgregar");
		
	}
	
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void testQueMuestraDatosDeCliente() {
		
		List<Estacionamiento> listaMock = new ArrayList<>();
		listaMock.add(estMock);
		Long idClienteMock = 5L;
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("id")).thenReturn(idClienteMock);

		when(servicioClienteMock.consultarClientePorId((long) 5)).thenReturn(clienteMock);
		when(servicioBilleteraMock.consultarBilleteraDeCliente(clienteMock)).thenReturn(billeteraMock);
		when(servicioEstMock.buscarEstacionamientoPorCliente(clienteMock)).thenReturn(listaMock);
		
		
		ModelAndView vista = (controladorClientesMock.mostrarDatosCliente(requestMock));
		assertThat(vista.getViewName()).isEqualTo("miPerfil");
	}
	
	
	
	
	
	
	
	

}
