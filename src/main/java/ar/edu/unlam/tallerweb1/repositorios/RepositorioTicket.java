<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Ticket;

public interface RepositorioTicket {

	Long calcularDias(String desde, String hasta);
	
	Long calcularHoras(String desde, String hasta);
	
	Double calcularPrecioPorEstadia(Double precio, String desde, String hasta);
	
	Double calcularPrecioPorHora(Double precio, String desde, String hasta);
	
	void registrarTicket(Ticket ticket);
	
	List<Garage> consultarGarage();
	
	Garage contultarUnGarage(Garage garage1);
	
}
=======
package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Ticket;

public interface RepositorioTicket {

	Long calcularDias(String desde, String hasta);
	
	Long calcularHoras(String desde, String hasta);
	
	Double calcularPrecioPorEstadia(Double precio, String desde, String hasta);
	
	Double calcularPrecioPorHora(Double precio, String desde, String hasta);
	
	void registrarTicket(Ticket ticket);
	
	List<Garage> consultarGarage();
	
	Garage contultarUnGarage(Garage garage1);
	
	Ticket buscarTicketPertenencienteAlGarage(Long id);
	
}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Ticket;

public interface RepositorioTicket {

	Long calcularDias(String desde, String hasta);
	
	Long calcularHoras(String desde, String hasta);
	
	Double calcularPrecioPorEstadia(Double precio, String desde, String hasta);
	
	Double calcularPrecioPorHora(Double precio, String desde, String hasta);
	
	void registrarTicket(Ticket ticket);
	
	List<Garage> consultarGarage();
	
	Garage contultarUnGarage(Garage garage1);
	
}
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
