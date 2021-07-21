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

public class ServicioAutoImpl implements ServicioAuto {

	private RepositorioAuto repositorioAuto;

	@Autowired
	public ServicioAutoImpl(RepositorioAuto servicioAuto) {

		this.repositorioAuto = servicioAuto;
	}

	@Override
	public Auto consultarAuto(Auto auto) {

		return repositorioAuto.consultarAuto(auto);
	}

	@Override
	public List<Auto> listaDeAutos() {

		return repositorioAuto.listaDeAutos();
	}

	@Override
	public void registrarAuto(Auto auto) {
		repositorioAuto.registrarAuto(auto);

	}

	@Override
	public List<Auto> consultarAutoDeCliente(Cliente cliente) {

		return repositorioAuto.ConsultarAutoDeCliente(cliente);

	}

	@Override
	public List<Auto> consultarAutoDeClienteActivo(Cliente cliente) {
		List<Auto> lista = repositorioAuto.ConsultarAutoDeCliente(cliente);
		ArrayList<Auto> activos = new ArrayList<Auto>();
		for (Auto e : lista) {
			if (e.getEnUso().equals(true)) {
				activos.add(e);
			}
		}
		return activos;

	}

	@Override
	public Auto buscarAuto(Long id) {

		return repositorioAuto.buscarAuto(id);
	}

	@Override
	public void cambiarEstadoDeSiestaEnGarageOno(Auto auto) {

		Auto auto1 = consultarAuto(auto);
		if (auto1.getUsandoGarage().equals(false)) {
			auto1.setUsandoGarage(true);
		} else {
			auto1.setUsandoGarage(false);
		}

	}

	@Override
	public void eliminarAuto(Auto auto) {
		repositorioAuto.eliminarAuto(auto);

	}

	@Override
	public void SacarAuto(Auto auto) {
		if (auto.getEnUso().equals(true)) {
			auto.setEnUso(false);
		}
	}

	@Override
	public Boolean existeAuto(Auto auto) {
		List<Auto> listaAutos = repositorioAuto.listaDeAutos();
		Boolean existe = false;
		for (Auto e : listaAutos) {
			if (e.getPatente().equals(auto.getPatente())) {
				existe = true;
				return existe;
			} else {
				existe = false;
			}
		}
		return existe;
	}

	@Override
	public void cambiarEstadoDeUso(Auto auto) {
		repositorioAuto.cambiarEstadoDeUso(auto);
	}

	@Override
	public List<Auto> consultarAutosSinGarage() {
		return repositorioAuto.consultarAutosSinGarage();
	}

	@Override
	public ArrayList<Auto> listaDeAutosDeClientesAfueraDeEst(Cliente cliente) {
		List<Auto> lista = consultarAutoDeClienteActivo(cliente);
		ArrayList<Auto> listaA = new ArrayList<Auto>();
		for (Auto e : lista) {
			if (e.getUsandoGarage().equals(false) && e.getReservado().equals(false)) {
				listaA.add(e);
			}
		}

		return listaA;
	}

	// Cambio Estado de Reserva
	@Override
	public void cambiarEstadoReservaAuto(Auto auto) {
		Auto auto1 = consultarAuto(auto);
		if (auto1.getReservado().equals(false)) {
			auto1.setReservado(true);
		} else {
			auto1.setReservado(false);
		}
	}

	@Override
	public List<Auto> consultarAutosSinGarageDeCliente(Cliente cliente) {
		
		return repositorioAuto.consultarAutosSinGarageDeCliente(cliente);
	}

	

}
