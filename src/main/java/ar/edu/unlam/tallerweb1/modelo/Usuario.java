package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en él
@Entity
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de dato de java.
	@OneToMany
	private List<Cliente> cliente;
	
<<<<<<< HEAD
	/*@OneToMany
	private List<DuenioCochera> duenio;
	*/
=======
	@OneToMany
	private List<DuenioCochera> duenio;
	
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Cliente> getCliente() {
		return cliente;
	}
	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}
<<<<<<< HEAD
	/*
=======
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
	public List<DuenioCochera> getDuenio() {
		return duenio;
	}
	public void setDuenio(List<DuenioCochera> duenio) {
		this.duenio = duenio;
	}
	
	
<<<<<<< HEAD
	*/
=======
	
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb

}
