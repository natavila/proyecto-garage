import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;

public class testLocalidad extends SpringTest{
	@Inject
	private SessionFactory sessionFactory;
	
	@Test
    @Transactional 
    @Rollback
    public void queSePuedaRegistrarUnaLocalidad() {
		
		
		}
	
}
