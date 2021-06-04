package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class ServicioUsuarioImpl implements ServicioUsuario{

	RepositorioUsuario servicioUsuario;
	
	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}
	@Override
	public void registrarUsuario(Usuario usuario) {
		
		servicioUsuario.registrarUsuario(usuario);
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {
	
		return servicioUsuario.consultarUsuario(usuario);
	}

}
