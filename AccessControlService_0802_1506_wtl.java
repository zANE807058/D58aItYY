// 代码生成时间: 2025-08-02 15:06:10
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class AccessControlService {
    
    // Get the session factory
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static void shutdown() {
        // Close the SessionFactory when you're done with it
        sessionFactory.close();
    }
    
    // Check user access rights for a given resource
    public boolean checkAccess(String username, String resource) {
        boolean hasAccess = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            // Query to check user's access rights
            String hql = "SELECT COUNT(*) FROM AccessRight ar WHERE ar.username = :username AND ar.resource = :resource";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("resource", resource);
            
            Long count = (Long) query.uniqueResult();
            hasAccess = count > 0;
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return hasAccess;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        try {
            // Test the access control service
            AccessControlService acService = new AccessControlService();
            boolean access = acService.checkAccess("john", "file1");
            System.out.println("Access granted: " + access);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            AccessControlService.shutdown();
        }
    }
}
