// 代码生成时间: 2025-09-20 11:43:33
 * HibernateIntegrationTest.java
 * 
 * @author YourName
 * @version 1.0
 * @since 2023-04-01
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Environment;
import java.util.Properties;
import org.hibernate.query.Query;

public class HibernateIntegrationTest {

    /**
     * Creates a new instance of HibernateIntegrationTest.
     */
    public HibernateIntegrationTest() {
    }

    /**
     * The main method for the integration test.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
            SessionFactory sessionFactory = new Configuration().configure(serviceRegistry).buildSessionFactory(serviceRegistry);
            
            // Create a Session from the SessionFactory
            Session session = sessionFactory.openSession();
            Transaction transaction = null;
            
            try {
                transaction = session.beginTransaction();
                // Perform database operations here
                // For example: Query execution, entity creation, etc.
                
                // Commit the transaction
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
                sessionFactory.close();
                serviceRegistry.close();
            }
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + " It is likely you have not configured your hibernate properties correctly." + "Exception: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Execute a query using Hibernate.
     * 
     * @param sessionFactory the sessionFactory to use.
     * @param hql the HQL string to execute.
     * @return a List of results.
     */
    public static List executeQuery(SessionFactory sessionFactory, String hql) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery(hql);
            return query.list();
        } finally {
            session.close();
        }
    }
}
