package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface ServicioCliente {
	
	
	public Cliente pagarReserva();

	public Cliente elegirUnGaraje();

	public Cliente elegirUnLugarParaEstacionar();

	public Cliente consultarClientePorId(Long id);
	
	Cliente consultarCliente(Cliente cliente);
	Cliente buscarCliente(Cliente cliente);

	ArrayList<Cliente> listaCliente();

	Integer notificadorDeClientesNuevos();
}
