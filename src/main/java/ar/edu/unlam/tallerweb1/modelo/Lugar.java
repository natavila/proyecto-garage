<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Lugar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer[][] lugares;
	
	@ManyToOne
	private Garage garage;
	
	@OneToOne
	private Auto auto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer[][] getLugares() {
		return lugares;
	}

	public void setLugares(Integer[][] lugares) {
		this.lugares = lugares;
	}

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
	
	
}
=======
package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Lugar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer[][] lugares;
	
	@ManyToOne
	private Garage garage;
	
	@OneToOne
	private Auto auto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer[][] getLugares() {
		return lugares;
	}

	public void setLugares(Integer[][] lugares) {
		this.lugares = lugares;
	}

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
	
	
}
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
=======
>>>>>>> branch 'ramaNataly' of https://github.com/natavila/proyecto-garage.git
package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Lugar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer[][] lugares;
	
	@ManyToOne
	private Garage garage;
	
	@OneToOne
	private Auto auto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer[][] getLugares() {
		return lugares;
	}

	public void setLugares(Integer[][] lugares) {
		this.lugares = lugares;
	}

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
	
	
}
=======
package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Lugar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer[][] lugares;
	
	@ManyToOne
	private Garage garage;
	
	@OneToOne
	private Auto auto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer[][] getLugares() {
		return lugares;
	}

	public void setLugares(Integer[][] lugares) {
		this.lugares = lugares;
	}

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
	
	
}
>>>>>>> refs/remotes/origin/SantosGaston
<<<<<<< HEAD
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
=======
>>>>>>> branch 'ramaNataly' of https://github.com/natavila/proyecto-garage.git
