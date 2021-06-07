package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	@OneToMany
	private List<Cliente> clientesAsignadosAlPlan;
	private Long cantidadAutosPermitidos;
	private Long cantidadHorasPermitidas;
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

	public List<Cliente> getClientesAsignadosAlPlan() {
		return clientesAsignadosAlPlan;
	}

	public void setClientesAsignadosAlPlan(List<Cliente> clientesAsignadosAlPlan) {
		this.clientesAsignadosAlPlan = clientesAsignadosAlPlan;
	}

	public Long getCantidadAutosPermitidos() {
		return cantidadAutosPermitidos;
	}

	public void setCantidadAutosPermitidos(Long cantidadAutosPermitidos) {
		this.cantidadAutosPermitidos = cantidadAutosPermitidos;
	}

	public Long getCantidadHorasPermitidas() {
		return cantidadHorasPermitidas;
	}

	public void setCantidadHorasPermitidas(Long cantidadHorasPermitidas) {
		this.cantidadHorasPermitidas = cantidadHorasPermitidas;
	}

	public Boolean getEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(Boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

}
