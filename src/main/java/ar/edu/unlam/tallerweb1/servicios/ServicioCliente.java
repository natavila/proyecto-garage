package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface ServicioCliente {
	
	
	public Cliente pagarReserva();

	public Cliente elegirUnGaraje();

	public Cliente elegirUnLugarParaEstacionar();

	public Cliente consultarClientePorId(Long id);
	
	

}
