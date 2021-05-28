package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Billetera;

public interface ServicioBilletera {
	
	void registrarBilletera(Billetera billetera);

	Billetera buscarBilleteraPorId(Long id);
	
	Double pagarReserva(Double precio, Double saldo);
	
	Double consultarSaldo(Billetera saldo);
	
	void ingresarSaldo(Billetera billetera, Double monto);
	
}
