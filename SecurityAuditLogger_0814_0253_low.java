// 代码生成时间: 2025-08-14 02:53:35
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Properties;

public class SecurityAuditLogger {

    // Define the entity class for the security audit log
    public static class SecurityAuditLog {
        private Long id;
        private String username;
        private String action;
        private String timestamp;

        // Standard getters and setters
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getAction() {
            return action;
        }
        public void setAction(String action) {
            this.action = action;
        }
        public String getTimestamp() {
            return timestamp;
        }
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }

    // Singleton instance of the SessionFactory
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            serviceRegistry = new StandardServiceRegistryBuilder().configure().buildServiceRegistry();
            sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().getSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    // Method to log a security audit event
    public static void logSecurityEvent(String username, String action) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Create a new security audit log entry
            SecurityAuditLog auditLog = new SecurityAuditLog();
            auditLog.setUsername(username);
            auditLog.setAction(action);
            auditLog.setTimestamp(String.valueOf(System.currentTimeMillis()));

            // Save the audit log entry to the database
            session.save(auditLog);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        logSecurityEvent("admin", "logged in");
    }
}
