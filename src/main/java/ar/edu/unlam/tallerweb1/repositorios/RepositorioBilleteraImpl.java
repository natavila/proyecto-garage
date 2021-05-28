package ar.edu.unlam.tallerweb1.repositorios;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Billetera;

@Repository("repositorioBilletera")
public class RepositorioBilleteraImpl implements RepositorioBilletera{

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioBilleteraImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void registrarBilletera(Billetera billetera) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(billetera);
		
	}
	@Override
	public Billetera buscarBilleteraPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return session.get(Billetera.class, id);
	}

	@Override
	public Double pagarReserva(Double precio, Double saldo) {
		
		Double montoAPagar = saldo - precio;
		
		return montoAPagar;
	}

	@Override
	public Double consultarSaldo(Billetera saldo) {
		
		return saldo.getSaldo();
	}

	@Override
	public void ingresarSaldo(Billetera billetera, Double monto) {
		
		 billetera.setSaldo(monto);
	}
	

}
