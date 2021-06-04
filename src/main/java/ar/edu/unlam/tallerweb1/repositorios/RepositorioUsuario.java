package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioUsuario {

	public void registrarUsuario(Usuario usuario);
	public Usuario consultarUsuario(Usuario usuario);
	
}
