package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEstacionamiento;

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

	@Override
	public Cliente consultarCliente(Cliente cliente) {
		
		return servicioCliente.consultarCliente(cliente);
	}
	@Override
	public ArrayList<Cliente> listaCliente(){
		return servicioCliente.listaDeClientes();
	}

	@Override
	public Cliente buscarCliente(Cliente cliente) {
		
		return servicioCliente.buscarCliente(cliente);
	}
	

	@Override
	public Integer notificadorDeClientesNuevos() {
		 Integer contador = servicioCliente.listaDeClientes().size();
		 return contador;
	}

	@Override
	public void modificarDatosCliente(Cliente cliente, Cliente modificado) {
		servicioCliente.modificarDatosCliente(cliente, modificado);
	}

	@Override
	public Boolean verificarPassword(Cliente cliente) {
		
		return servicioCliente.verificarPassword(cliente);
	}
	

}
