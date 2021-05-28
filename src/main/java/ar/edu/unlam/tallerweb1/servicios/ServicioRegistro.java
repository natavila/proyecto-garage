<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;

public interface ServicioRegistro {

	
	public abstract void agregarCliente(Cliente cliente);
	
	public abstract void registrarAuto(Auto auto);
	
	public abstract void asignarAuto(Auto auto, Cliente cliente);
	
	public abstract void registrarGarage(Garage garage);
	
	public abstract void registrarLocalidad(Localidad localidad);
	
	public abstract List<Cliente> listaCliente();
	
	public abstract Cliente consultarClientePorId(Long id);

	List<Auto> listaAuto();
	
	public abstract Auto consultarAuto(Auto auto);
}
=======
package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;

public interface ServicioRegistro {

	
	public abstract void agregarCliente(Cliente cliente);
	
	public abstract void registrarAuto(Auto auto);
	
	public abstract void asignarAuto(Auto auto, Cliente cliente);
	
	public abstract void registrarGarage(Garage garage);
	
	public abstract void registrarLocalidad(Localidad localidad);
	
	public abstract List<Cliente> listaCliente();
	
	public abstract Cliente consultarClientePorId(Long id);

	List<Auto> listaAuto();
	
	public abstract Auto consultarAuto(Auto auto);
}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
