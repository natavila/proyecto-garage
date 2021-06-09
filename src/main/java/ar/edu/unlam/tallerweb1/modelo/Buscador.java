package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.ManyToOne;

public class Buscador {
	private String palabraBuscada;
	@ManyToOne
	private Cliente cliente;
	
	
	public String getPalabraBuscada() {
		return palabraBuscada;
	}
	public void setPalabraBuscada(String palabraBuscada) {
		this.palabraBuscada = palabraBuscada;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}

