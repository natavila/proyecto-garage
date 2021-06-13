package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;

@Transactional
@Service

public class ServicioAutoImpl implements ServicioAuto{

	private RepositorioAuto servicioAuto;
	
	@Autowired
	public ServicioAutoImpl(RepositorioAuto servicioAuto) {
		
		this.servicioAuto = servicioAuto;
	}
	@Override
	public Auto consultarAuto(Auto auto) {
		
		return servicioAuto.consultarAuto(auto);
	}
	@Override
	public List<Auto> listaDeAutos() {
		// TODO Auto-generated method stub
		return servicioAuto.listaDeAutos();
	}
	@Override
	public void registrarAuto(Auto auto) {
		servicioAuto.registrarAuto(auto);
		
	}
	@Override
	public List<Auto> consultarAutoDeCliente(Cliente cliente) {
		
		return servicioAuto.ConsultarAutoDeCliente( cliente);
	}
	@Override
	public Auto buscarAuto(Long id) {
	
		return servicioAuto.buscarAuto(id);
	}
	@Override
	public void cambiarEstadoDeSiestaEnGarageOno(Auto auto) {

		servicioAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
		
	}
	
	
	
	
	
	
	

}
