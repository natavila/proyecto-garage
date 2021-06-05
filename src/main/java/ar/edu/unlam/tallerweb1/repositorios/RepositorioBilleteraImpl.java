package ar.edu.unlam.tallerweb1.repositorios;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Billetera;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Estacionamiento;
import ar.edu.unlam.tallerweb1.modelo.Garage;
import ar.edu.unlam.tallerweb1.servicios.ServicioCobrarTicketsImpl;

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
	public void pagarReservaEstadia(Estacionamiento estacionamiento, Billetera billetera) {
		RepositorioEstacionamientoImpl repositorioEstacionamiento = new RepositorioEstacionamientoImpl(sessionFactory);
		ServicioCobrarTicketsImpl servicioCobrarTickets = new ServicioCobrarTicketsImpl(repositorioEstacionamiento);
		
		final Session session = sessionFactory.getCurrentSession();
		Double montoAPagar = billetera.getSaldo() - servicioCobrarTickets.calcularPrecioPorEstadia(estacionamiento.getPrecioAPagar(), estacionamiento);
		billetera.setSaldo(montoAPagar);
		session.update(billetera);
	}
	
	@Override
	public void pagarReservaHora(Garage garage, Billetera billetera) {
		final Session session = sessionFactory.getCurrentSession();
		Double montoAPagar = billetera.getSaldo() - garage.getPrecioHora();
		billetera.setSaldo(montoAPagar);
		session.update(billetera);
	}

	@Override
	public Double consultarSaldo(Billetera saldo) {
		
		return saldo.getSaldo();
	}

	@Override
	public void ingresarSaldo(Billetera billetera, Double monto) {
		final Session session = sessionFactory.getCurrentSession();
		 Double saldoActual = billetera.getSaldo() + monto;
		 billetera.setSaldo(saldoActual);
		 session.update(billetera);
	}
	
	@Override
	public Billetera consultarBilleteraDeCliente(Cliente cliente){
		
		final Session session = sessionFactory.getCurrentSession();
		return (Billetera) session.createCriteria(Billetera.class)
				.createAlias("cliente", "clienteBuscado")
				.add(Restrictions.eq("clienteBuscado.id", cliente.getId()))
				.uniqueResult();
	}

	@Override
	public List<Billetera> consultarBilleteras() {
		  final Session session = sessionFactory.getCurrentSession();
		   List<Billetera> listaBilletera = session.createCriteria(Billetera.class)
				  .list();
		   return listaBilletera;
	}

	

	

}
