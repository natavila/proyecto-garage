package ar.edu.unlam.tallerweb1.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTicket;

@Service
@Transactional
public class ServicioClienteImpl implements ServicioCliente{

	private RepositorioCliente servicioCliente;
	
	@Autowired
	public ServicioClienteImpl(RepositorioCliente servicioCliente){
		this.servicioCliente = servicioCliente;
	}
	
	@Override
	public Cliente pagarReserva() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente elegirUnGaraje() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente elegirUnLugarParaEstacionar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente consultarClientePorId(Long id) {
		
		return servicioCliente.consultarPorId(id);
	}

}
