<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface ServicioCliente {
	
	
	public Cliente pagarReserva();

	public Cliente elegirUnGaraje();

	public Cliente elegirUnLugarParaEstacionar();

	public Cliente consultarClientePorId(Long id);
	
	

}
=======
package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface ServicioCliente {
	
	
	public Cliente pagarReserva();

	public Cliente elegirUnGaraje();

	public Cliente elegirUnLugarParaEstacionar();

	public Cliente consultarClientePorId(Long id);
	
	public Cliente buscarClientePorDni(Integer dni);
	
	

}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cliente;

public interface ServicioCliente {
	
	
	public Cliente pagarReserva();

	public Cliente elegirUnGaraje();

	public Cliente elegirUnLugarParaEstacionar();

	public Cliente consultarClientePorId(Long id);
	
	

}
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
