// 代码生成时间: 2025-09-09 17:47:32
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Interceptor;
import org.hibernate.EmptyInterceptor;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import java.util.Properties;
import java.util.List;
import java.util.Map;

public class CacheStrategyDemo {

    // Hibernate SessionFactory
    private static SessionFactory sessionFactory;

    // Private Constructor to prevent instantiation
    private CacheStrategyDemo() {
    }

    // Static block to create and configure the SessionFactory
    static {
        // Create the SessionFactory from hibernate.cfg.xml
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + "
" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Method to get the current SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Method to close the SessionFactory
    public static void shutdown() {
        getSessionFactory().close();
    }

    // Demonstration of cache usage
    public static void main(String[] args) {
        try {
            // Get the current session
            Session session = getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                // Set cache usage to 'ignore' to skip cache
                session.setCacheMode(CacheMode.IGNORE);

                // Fetch some data
                Query query = session.createQuery("SELECT e FROM Employee e");
                List results = query.list();
                for (Object obj : results) {
                    System.out.println(obj);
                }

                // Set cache usage to 'use' to use cache
                session.setCacheMode(CacheMode.USE);

                // Fetch the same data again, should be retrieved from cache
                query = session.createQuery("SELECT e FROM Employee e");
                results = query.list();
                for (Object obj : results) {
                    System.out.println(obj);
                }

                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

// The Employee entity class
class Employee {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
