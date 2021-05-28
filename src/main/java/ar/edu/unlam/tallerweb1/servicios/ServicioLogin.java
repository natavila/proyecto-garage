package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Cliente consultarCliente(Cliente cliente);
	
	Cliente verificarCorreo(Cliente cliente);
	
	Cliente consultarClientePorId(Long cliente);
	
	List<Cliente> listaDeClientes();
	
}
