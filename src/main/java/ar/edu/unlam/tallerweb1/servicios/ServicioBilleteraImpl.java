package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioBilletera;

@Service
@Transactional
public class ServicioBilleteraImpl implements ServicioBilletera{

	private RepositorioBilletera servicioBilletera;
	
	@Autowired
	public ServicioBilleteraImpl(RepositorioBilletera servicioBilletera) {

		this.servicioBilletera = servicioBilletera;
	}

	@Override
	public Billetera buscarBilleteraPorId(Long id) {
		
		return servicioBilletera.buscarBilleteraPorId(id);
	}

	@Override
	public void pagarReservaEstadia(Estacionamiento estacionamiento, Billetera billetera) {
		
		 servicioBilletera.pagarReservaEstadia(estacionamiento, billetera);	
	}

	@Override
	public Double consultarSaldo(Billetera saldo) {
		
		return servicioBilletera.consultarSaldo(saldo);
	}

	@Override
	public void ingresarSaldo(Billetera billetera, Double monto) {
		
		servicioBilletera.ingresarSaldo(billetera, monto);
		
	}

	@Override
	public void registrarBilletera(Billetera billetera) {
		if(servicioBilletera.consultarBilleteraDeCliente(billetera.getCliente()) == null) 
		servicioBilletera.registrarBilletera(billetera);
	}

	@Override
	public Billetera consultarBilleteraDeCliente(Cliente cliente) {
		
		return servicioBilletera.consultarBilleteraDeCliente(cliente);
	}

	@Override
	public List<Billetera> consultarBilleteras() {
		
		return servicioBilletera.consultarBilleteras();
	}

	@Override
	public void pagarReservaPorHora(Estacionamiento estacionamiento, Billetera billetera) {
		
		servicioBilletera.pagarReservaHora(estacionamiento, billetera);
		
	}

	@Override
	public void pagarPlan(Plan plan, Billetera billetera) {
		
		servicioBilletera.pagarPlan(plan, billetera);
		
	}

	

}
