package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface ServicioAuto{
	
	public Auto consultarAuto(Auto auto);
	List<Auto> listaDeAutos();

	void registrarAuto(Auto auto);
	List<Auto> consultarAutoDeCliente(Cliente cliente);
	public Auto buscarAuto(Long id);
	void cambiarEstadoDeSiestaEnGarageOno(Auto auto);
	void eliminarAuto(Auto auto);
	List<Auto> consultarAutoDeClienteActivo(Cliente cliente);
	void SacarAuto(Auto auto);
	void cambiarEstadoDeUso(Auto auto);
	Boolean existeAuto(Auto auto);
	public List<Auto> consultarAutosSinGarage();
	//List<Auto> consultarAutoSinGarageDeCliente(Cliente cliente);
	ArrayList<Auto> listaDeAutosDeClientesAfueraDeEst(Cliente cliente);
	void cambiarEstadoReservaAuto(Auto auto);
	public List<Auto> consultarAutosSinGarageDeCliente(Cliente cliente);
}
