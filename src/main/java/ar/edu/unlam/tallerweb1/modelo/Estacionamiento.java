package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Estacionamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate fechaOperacion = LocalDate.now();
	private LocalTime horaOperacion = LocalTime.now();
	
	private String fechaDesde;
	private String fechaHasta;
	private String horaDesde;
	private String horaHasta;
	private Double precioAPagar;
	private Boolean activa;

	@ManyToOne
	private Garage garage1;
	@ManyToOne
	private Auto auto;
	
	public Double getPrecioAPagar() {
		return precioAPagar;
	}
	public void setPrecioAPagar(Double precioAPagar) {
		this.precioAPagar = precioAPagar;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}
	public String getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(LocalDate fecha) {
		this.fechaOperacion = fecha;
	}
	public LocalTime getHoraOperacion() {
		return horaOperacion;
	}
	public void setHoraOperacion(LocalTime horaEntrada) {
		this.horaOperacion = horaEntrada;
	}
	
	public Garage getGarage1() {
		return garage1;
	}
	public void setGarage1(Garage garage1) {
		this.garage1 = garage1;
	}
	public Auto getAuto() {
		return auto;
	}
	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	public Boolean getActiva() {
		return activa;
	}
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
	
	
	
	
}
