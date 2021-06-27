package ar.edu.unlam.tallerweb1.servicios;


import java.util.ArrayList;
import java.util.HashSet;
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
		
		return servicioAuto.listaDeAutos();
	}
	@Override
	public void registrarAuto(Auto auto) {
		servicioAuto.registrarAuto(auto);
		
	}
	@Override
	public List<Auto> consultarAutoDeCliente(Cliente cliente) {
		
		
		return servicioAuto.ConsultarAutoDeCliente(cliente);
		
	}
	
	
	@Override
	public List<Auto> consultarAutoDeClienteActivo(Cliente cliente){
		List<Auto> lista= servicioAuto.ConsultarAutoDeCliente(cliente);
		ArrayList<Auto> activos = new ArrayList<Auto>();
		for(Auto e: lista) {
			if(e.getEnUso().equals(true)) {
				activos.add(e);
			}
		}
		return activos;
			
	}
	@Override
	public Auto buscarAuto(Long id) {
	
		return servicioAuto.buscarAuto(id);
	}
	@Override
	public void cambiarEstadoDeSiestaEnGarageOno(Auto auto) {

		
		
		servicioAuto.cambiarEstadoDeSiestaEnGarageOno(auto);
		
	}
	@Override
	public void eliminarAuto(Auto auto) {
		servicioAuto.eliminarAuto(auto);
		
	}
	
	@Override
	public void SacarAuto(Auto auto) {
		if(auto.getEnUso().equals(true)) {
			auto.setEnUso(false);
		}
	}
	@Override
	public Boolean existeAuto(Auto auto) {
		List<Auto> listaAutos =servicioAuto.listaDeAutos();
		Boolean existe=false;
		for(Auto e: listaAutos) {
			if(e.getPatente().equals(auto.getPatente())) {
				existe = true;
				return existe;
			}else {
				existe=false;
			}
		}
		return existe;
	}
	@Override
	public void cambiarEstadoDeUso(Auto auto) {
		servicioAuto.cambiarEstadoDeUso(auto);
		
	}
	@Override
	public List<Auto> consultarAutosSinGarage() {
		return servicioAuto.consultarAutosSinGarage();
	}
	
	@Override
	public ArrayList<Auto> listaDeAutosDeClientesAfueraDeEst(Cliente cliente){
		List<Auto> lista = consultarAutoDeClienteActivo(cliente);
		ArrayList<Auto> listaA= new ArrayList<Auto>();
		for(Auto e: lista) {
			if(e.getUsandoGarage().equals(false)) {
				listaA.add(e);
			}
		}
		
		return listaA;
	}
	
	
	
	
	
	

}
