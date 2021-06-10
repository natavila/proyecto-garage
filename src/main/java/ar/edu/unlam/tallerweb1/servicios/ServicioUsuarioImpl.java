package ar.edu.unlam.tallerweb1.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioUsuario")
@Transactional
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
	public Usuario consultarUsuarioPorId(Usuario usuario) {
	
		return servicioUsuario.consultarUsuarioPorId(usuario);
	}
	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		
		return servicioUsuario.consultarUsuario(usuario);
	}

}
