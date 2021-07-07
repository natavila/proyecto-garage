package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.servicios.ServicioBilletera;
import ar.edu.unlam.tallerweb1.servicios.ServicioCliente;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstacionamiento;
import ar.edu.unlam.tallerweb1.servicios.ServicioGarage;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistro;

public class testMockitoPagarReserva extends SpringTest{

	private ServicioLogin servicioLogin;
	private ServicioEstacionamiento servEst;
	private ServicioBilletera servicioBilletera;
	private ServicioGarage servicioGarage;
	private ServicioCliente servicioCliente;
	private ServicioRegistro servicioRegistro;
}
