package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Garage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String calle;
	private Integer capacidad;
	private Integer numero;
	private Double precioHora;
	private Double precioEstadia;
	private String horario;
	private String localidad;
	private String latitud;
	private String longitud;
	private Integer contador = 0;
	private Boolean activo= true;
	@OneToMany
	private List<Lugar>lugares;
	
	/*@ManyToOne
	private Localidad localidad;*/
	
	 
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}
	
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public List<Lugar> getLugares() {
		return lugares;
	}
	public void setLugares(List<Lugar> lugares) {
		this.lugares = lugares;
	}
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
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	public Double getPrecioHora() {
		return precioHora;
	}
	public void setPrecioHora(Double precioHora) {
		this.precioHora = precioHora;
	}
	public Double getPrecioEstadia() {
		return precioEstadia;
	}
	public void setPrecioEstadia(Double precioEstadia) {
		this.precioEstadia = precioEstadia;
	}

	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	
	
	
	
	
	
	
}
