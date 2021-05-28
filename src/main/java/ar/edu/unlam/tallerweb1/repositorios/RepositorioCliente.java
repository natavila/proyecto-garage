package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.DuenioCochera;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Ticket;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioCliente {
	
	Cliente consultarCliente (Cliente cliente);
	
	Cliente consultarPorId(Long id);
	
<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
<<<<<<< HEAD
=======
	Cliente buscarClientePorDni(Integer dni);
	
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
	Cliente verificarCorreo(Cliente cliente);
	
	void registrarCliente(Cliente cliente);
	
	public void registrarAuto(Auto auto);
	
	List<Cliente> listaDeClientes();
	
	List<Auto> listaDeAutos();
	
	public Auto consultarAuto(Auto auto);
	
	// El usuario podra pagar la reserva solo si eligio un garaje, lugar de estacionamiento y plan de alquiler.
	Cliente pagarReserva(Ticket  ticket);
	
	// Se mostrara en un mapa todos los garajes que esten registrados en el sistema y el usuario  podra elegir uno de ellos.
	// Al seleccionarlo se desplegara una ventana donde se detalla el garaje como su nombre y planes de alquiler(por hora, semana o mes).
	Cliente elegirUnGaraje();
	
	// Se mostrara un mapa del estacionamiento donde se visualizara los lugares ocupados y libres.
	// A su vez se mostrara el horario en el que sera desocupado el lugar.
	Cliente elegirUnLugarParaEstacionar();

	
}
