import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.test.context.TestExecutionListeners;

import com.customer.listener.HibernateListener;
import com.customer.model.Customer;

import junit.framework.TestCase;


public class CustomerTest extends TestCase {

	private Customer[] customers = new Customer[3];
	private int customer_count;
	private Session session;

	protected void setUp() throws Exception {
		super.setUp();

		
		customers[0] = new Customer("Customer 1", "Casablanca, Morocco",
				new Date());
		customers[1] = new Customer("Customer 2", "Rabat, Morocco", new Date());
		customers[2] = new Customer("Tanger 3", "Tanger, Morocco", new Date());
		session = HibernateListener.getSessionFactory().openSession();

	}

	
	public void testAddCustomer() {
		session.beginTransaction();
		session.save(customers[0]);
		session.getTransaction().commit();
		assertEquals(++customer_count, session.createQuery("from Customer")
				.list().size());
	}

}