<<<<<<< HEAD
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
	private Double precioMes;
	private String horario;
	private String localidad;
	private Integer contador = 0;
	
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
	/*public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}*/
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
	public Double getPrecioMes() {
		return precioMes;
	}
	public void setPrecioMes(Double precioMes) {
		this.precioMes = precioMes;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
}
=======
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
	private Double precioMes;
	private String horario;
	private String localidad;
	private Integer contador = 0;
	
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
	/*public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}*/
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
	public Double getPrecioMes() {
		return precioMes;
	}
	public void setPrecioMes(Double precioMes) {
		this.precioMes = precioMes;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
}
>>>>>>> refs/remotes/origin/SantosGaston
