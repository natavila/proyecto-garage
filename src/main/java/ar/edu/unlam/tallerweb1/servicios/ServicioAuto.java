package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface ServicioAuto{
	
	public Auto consultarAuto(Auto auto);
	List<Auto> listaDeAutos();

	void registrarAuto(Auto auto);
	public List<Auto> consultarAutoDeCliente(Cliente cliente);

}
