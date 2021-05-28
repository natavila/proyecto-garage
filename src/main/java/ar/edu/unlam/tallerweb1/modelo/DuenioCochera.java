<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DuenioCochera{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cuit;
	private String nombreGarage;
	
	@ManyToOne
	private Usuario usuariosDuenioCochera;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCuit() {
		return cuit;
	}

	public void setCuit(Integer cuit) {
		this.cuit = cuit;
	}

	public String getNombreGarage() {
		return nombreGarage;
	}

	public void setNombreGarage(String nombreGarage) {
		this.nombreGarage = nombreGarage;
	}

	public Usuario getDuenioCochera() {
		return usuariosDuenioCochera;
	}

	public void setDuenioCochera(Usuario duenioCochera) {
		this.usuariosDuenioCochera = duenioCochera;
	}

	public Usuario getUsuariosDuenioCochera() {
		return usuariosDuenioCochera;
	}

	public void setUsuariosDuenioCochera(Usuario usuariosDuenioCochera) {
		this.usuariosDuenioCochera = usuariosDuenioCochera;
	}
	
	
}
=======
package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DuenioCochera{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cuit;
	private String nombreGarage;
	
	@ManyToOne
	private Usuario usuariosDuenioCochera;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCuit() {
		return cuit;
	}

	public void setCuit(Integer cuit) {
		this.cuit = cuit;
	}

	public String getNombreGarage() {
		return nombreGarage;
	}

	public void setNombreGarage(String nombreGarage) {
		this.nombreGarage = nombreGarage;
	}

	public Usuario getDuenioCochera() {
		return usuariosDuenioCochera;
	}

	public void setDuenioCochera(Usuario duenioCochera) {
		this.usuariosDuenioCochera = duenioCochera;
	}

	public Usuario getUsuariosDuenioCochera() {
		return usuariosDuenioCochera;
	}

	public void setUsuariosDuenioCochera(Usuario usuariosDuenioCochera) {
		this.usuariosDuenioCochera = usuariosDuenioCochera;
	}
	
	
}
>>>>>>> refs/remotes/origin/SantosGaston
