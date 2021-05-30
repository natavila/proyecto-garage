package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;

public interface ServicioRegistro {

	
	public abstract void agregarCliente(Cliente cliente);
	
	public abstract void asignarAuto(Auto auto, Cliente cliente);
	
	public abstract void registrarGarage(Garage garage);
	
	public abstract void registrarLocalidad(Localidad localidad);
	
	public abstract List<Cliente> listaCliente();
	
	public abstract Cliente consultarClientePorId(Long id);

	
	
	
}
