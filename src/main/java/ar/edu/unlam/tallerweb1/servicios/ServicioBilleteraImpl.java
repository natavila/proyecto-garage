package ar.edu.unlam.tallerweb1.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Billetera;
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
	public Double pagarReserva(Double precio, Double saldo) {
		
		return servicioBilletera.pagarReserva(precio, saldo);	
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
		
		servicioBilletera.registrarBilletera(billetera);
	}

}
