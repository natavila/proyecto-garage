package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private Long cantidadAutosPermitidos;
	private Long cantidadHorasPermitidas;
	private Double precio;
	private Boolean estaActivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCantidadAutosPermitidos() {
		return cantidadAutosPermitidos;
	}

	/*public void setCantidadAutosPermitidos(Long cantidadAutosPermitidos) {
		this.cantidadAutosPermitidos = cantidadAutosPermitidos;
		this.cantidadAutosRestantes = cantidadAutosPermitidos;
	}*/

	public Long getCantidadHorasPermitidas() {
		return cantidadHorasPermitidas;
	}

	/*public void setCantidadHorasPermitidas(Long cantidadHorasPermitidas) {
		this.cantidadHorasPermitidas = cantidadHorasPermitidas;
		this.cantidadHorasRestantes = cantidadHorasPermitidas;
	}*/
	
//	public void actualizarEstado(Long horas) {
//		this.cantidadAutosRestantes--;
//		this.cantidadHorasRestantes = this.cantidadHorasRestantes - horas;
//	}

//	public Long getCantidadAutosRestantes() {
//		return cantidadAutosRestantes;
//	}
//
//	public void setCantidadAutosRestantes(Long cantidadAutosRestantes) {
//		this.cantidadAutosRestantes = cantidadAutosRestantes;
//	}
//
//	public Long getCantidadHorasRestantes() {
//		return cantidadHorasRestantes;
//	}
//
//	public void setCantidadHorasRestantes(Long cantidadHorasRestantes) {
//		this.cantidadHorasRestantes = cantidadHorasRestantes;
//	}

	public Boolean getEstaActivo() {
		return estaActivo;
	}

	public void setCantidadAutosPermitidos(Long cantidadAutosPermitidos) {
		this.cantidadAutosPermitidos = cantidadAutosPermitidos;
	}

	public void setCantidadHorasPermitidas(Long cantidadHorasPermitidas) {
		this.cantidadHorasPermitidas = cantidadHorasPermitidas;
	}

	public void setEstaActivo(Boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
