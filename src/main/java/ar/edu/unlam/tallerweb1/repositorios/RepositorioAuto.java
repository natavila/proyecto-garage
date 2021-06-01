package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface RepositorioAuto {

	public Auto consultarAuto(Auto auto);

	List<Auto> listaDeAutos();

	void registrarAuto(Auto auto);

	public List<Auto> ConsultarAutoDeCliente(Cliente cliente);

	Auto buscarAuto(Long id);
	
	
}
