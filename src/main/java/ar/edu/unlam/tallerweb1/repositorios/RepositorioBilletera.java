package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Plan;

public interface RepositorioBilletera {
	
	void registrarBilletera(Billetera billetera);
	
	Billetera buscarBilleteraPorId(Long id);
	
	Billetera consultarBilleteraDeCliente(Cliente cliente);
	
	void pagarReservaEstadia(Estacionamiento estacionamiento, Billetera billetera);
	
	void pagarReservaHora(Estacionamiento estacionamiento, Billetera billetera);
	
	void pagarPlan(Plan plan, Billetera billetera);
	
	Double consultarSaldo(Billetera saldo);
	
	void ingresarSaldo(Billetera billetera, Double monto);
	
	List<Billetera> consultarBilleteras(); 

}
