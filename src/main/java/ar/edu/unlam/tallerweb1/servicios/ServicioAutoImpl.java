<<<<<<< HEAD
package ar.edu.unlam.tallerweb1.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;

@Service
@Transactional
public class ServicioAutoImpl implements ServicioAuto{

	private RepositorioAuto servicioAuto;
	
	@Autowired
	public ServicioAutoImpl(RepositorioAuto servicioAuto) {
		
		this.servicioAuto = servicioAuto;
	}
	@Override
	public Auto consultarAuto(Auto auto) {
		
		return servicioAuto.consultarAuto(auto);
	}

}
=======
package ar.edu.unlam.tallerweb1.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAuto;

@Service
@Transactional
public class ServicioAutoImpl implements ServicioAuto{

	private RepositorioAuto servicioAuto;
	
	@Autowired
	public ServicioAutoImpl(RepositorioAuto servicioAuto) {
		
		this.servicioAuto = servicioAuto;
	}
	@Override
	public Auto consultarAuto(Auto auto) {
		
		return servicioAuto.consultarAuto(auto);
	}

}
>>>>>>> refs/remotes/origin/SantosGaston
