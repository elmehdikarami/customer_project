import java.net.URL;
import java.util.Date;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.customer.model.Customer;

public class TestCustomer extends TestCase {

	private int customer_count;

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
		customers[1] = new Customer(";Customer 2", "Tanger, Morocco",
				new Date());
		customers[2] = new Customer("Customer 3", "Rabat, Morocco", new Date());

	}

	@Test
	public void testone() {
		session.beginTransaction();
		session.save(customers[0]);
		session.getTransaction().commit();
		assertEquals(++customer_count, session.createQuery("from Customer")
				.list().size());
		assertEquals(1, 1);
	}

}