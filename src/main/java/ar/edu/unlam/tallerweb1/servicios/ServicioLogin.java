package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Administrador;
import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Cliente consultarCliente(Cliente cliente);
	
	Administrador consultarAdministrador(Administrador administrador);
	
	Cliente verificarCorreo(Cliente cliente);
	
	Cliente consultarClientePorId(Long cliente);
	
	List<Cliente> listaDeClientes();
	
}
