package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Administrador;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioCliente")
public class RepositorioClienteImpl implements RepositorioCliente{

	// Como todo repositorio maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioClienteImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
    
    @Override
	public void registrarCliente(Cliente cliente) {
		
		final Session session = sessionFactory.getCurrentSession();
		String nombreUpp = cliente.getNombre().toUpperCase();
		String apellidoUpp = cliente.getApellido().toUpperCase();
		String emailUpp = cliente.getEmail().toUpperCase();
		String locUpp = cliente.getLocalidad().toUpperCase();
		cliente.setNombre(nombreUpp);
		cliente.setApellido(apellidoUpp);
		cliente.setEmail(emailUpp);
		cliente.setLocalidad(locUpp);
		session.save(cliente);
		
	}
    
  

	@Override
	public Cliente consultarCliente(Cliente cliente) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Cliente) session.createCriteria(Cliente.class)
				.add(Restrictions.eq("email", cliente.getEmail()))
				.add(Restrictions.eq("password", cliente.getPassword()))
				.add(Restrictions.eq("activo", true))				
				.uniqueResult();
	}
	
	@Override 
	public Cliente buscarCliente(Cliente cliente) {
		final Session session = sessionFactory.getCurrentSession();
		return  (Cliente) session.createCriteria(Cliente.class)
				.add(Restrictions.eq("id",cliente.getId()))
				.uniqueResult();
		
	}

	@Override
	public Cliente verificarCorreo(Cliente cliente) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Cliente) session.createCriteria(Cliente.class)
				.add(Restrictions.eq("email", cliente.getEmail()))
				.uniqueResult();
	}
	
	@Override
	public Cliente pagarReserva(Estacionamiento ticket) {
		
		final Session session = sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public Cliente elegirUnGaraje() {
		return null;
	}

	@Override
	public Cliente elegirUnLugarParaEstacionar() {
		return null;
	}

	@Override
	public Cliente consultarPorId(Long id) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Cliente) session.createCriteria(Cliente.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		
	}

	@Override
	public ArrayList<Cliente> listaDeClientes() {
		
		 final Session session = sessionFactory.getCurrentSession();
		  ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		   listaClientes = (ArrayList<Cliente>) session.createCriteria(Cliente.class)
				   .add(Restrictions.eq("roll", "cliente"))
				  .list();
				return listaClientes;  
	}

	@Override
	public Administrador consultarAdministrador(Administrador administrador) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Administrador) session.createCriteria(Administrador.class)
				.add(Restrictions.eq("email", administrador.getEmail()))
				.add(Restrictions.eq("password", administrador.getPassword()))
				.uniqueResult();
	}

	@Override
	public Cliente existeUsuario(Cliente cliente) {
		final Session session = sessionFactory.getCurrentSession();
		return (Cliente) session.createCriteria(Cliente.class)
				.createAlias("usuario", "usuarioBuscado")
				.add(Restrictions.eq("usuarioBuscado.id", cliente.getId()))
				.uniqueResult();
	}

	@Override
	public void modificarDatosCliente(Cliente cliente, Cliente modificado) {
		final Session session = sessionFactory.getCurrentSession();
		String nombreUpp = cliente.getNombre().toUpperCase();
		String apellidoUpp = cliente.getApellido().toUpperCase();
		String emailUpp = cliente.getEmail().toUpperCase();
		String locUpp = cliente.getLocalidad().toUpperCase();
		modificado.setNombre(nombreUpp);
		modificado.setApellido(apellidoUpp);
		modificado.setEmail(emailUpp);
		modificado.setLocalidad(locUpp);
		session.update(modificado);
	}

	@Override
	public Boolean verificarPassword(Cliente cliente) {
		
		String passValidar = cliente.getPassword();
		char pass;
		byte contNumero = 0, contMayus = 0, contMin = 0;
		
		if(passValidar.length() >= 8) {
			for(byte i = 0; i < passValidar.length(); i++) {
				pass = passValidar.charAt(i);
				String passValue = String.valueOf(pass);
				if (passValue.matches("[A-Z]")) {
					contMayus++;
	            } else if (passValue.matches("[a-z]")) {
	            	contMin++;
	            } else if (passValue.matches("[0-9]")) {
	                contNumero++;
	            }
			}if(contNumero >= 1 && contMayus >= 1 && contMin >= 1) {
				return true;
			}
				return false;
		}else {
			return false;
		}
}

	



	

	




	

	

	

	

	


}
