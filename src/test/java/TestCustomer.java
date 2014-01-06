import java.net.URL;
import java.util.Date;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.customer.model.Customer;

public class TestCustomer extends TestCase {

	private static int customers_count;

	private static Configuration config;
	private static SessionFactory factory;
	private static Session session;

	Customer[] customers = new Customer[3];

	protected void setUp() throws Exception {
		super.setUp();

		URL url = TestCustomer.class.getResource("testhibernate.cfg.xml");
		config = new Configuration().configure(url);
		factory = config.buildSessionFactory();
		session = factory.openSession();
		customers[0] = new Customer("Customer 1", "Casablanca, Morocco",
				new Date());
		customers[1] = new Customer("Customer 2", "Tanger, Morocco", new Date());
		customers[2] = new Customer("Customer 3", "Rabat, Morocco", new Date());
		session.beginTransaction();
		session.save(customers[0]);
		session.save(customers[1]);
		session.getTransaction().commit();
		customers_count = 2;
	}

	@Test
	public void testAddCustomer() {
		session.beginTransaction();
		session.save(customers[2]);
		session.getTransaction().commit();
		assertEquals(customers_count + 1, session.createQuery("from Customer")
				.list().size());
	}


	@Test
	public void testRemoveCustomer() {
		session.beginTransaction();
		session.delete(customers[2]);
		session.getTransaction().commit(); 
		assertEquals(customers_count, session.createQuery("from Customer").list().size());
	}

}
