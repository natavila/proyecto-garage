package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Billetera;

public interface RepositorioBilletera {
	
	void registrarBilletera(Billetera billetera);
	
	Billetera buscarBilleteraPorId(Long id);
	
	Double pagarReserva(Double precio, Double saldo);
	
	Double consultarSaldo(Billetera saldo);
	
	void ingresarSaldo(Billetera billetera, Double monto);

}
