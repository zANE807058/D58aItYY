// 代码生成时间: 2025-09-07 22:44:02
// UserPermissionManagement.java
// This class represents a user permission management system using Hibernate framework.

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

// User entity class
class User {
    private int id;
    private String name;
    private String role; // Role can be 'admin', 'user', etc.

    public User() {}

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
a
// UserPermissionManagement class
public class UserPermissionManagement {
    // Method to create a new user
    public void addUser(String name, String role) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Create a new user
            User user = new User();
            user.setName(name);
            user.setRole(role);

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            sessionFactory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    // Method to update user role
    public void updateUserRole(int userId, String newRole) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Find the user by ID
            User user = session.get(User.class, userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + userId);
            }

            // Update the user's role
            user.setRole(newRole);

            // Save the updated user
            session.saveOrUpdate(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            sessionFactory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    // Method to delete a user
    public void deleteUser(int userId) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Find the user by ID
            User user = session.get(User.class, userId);
            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + userId);
            }

            // Delete the user
            session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            sessionFactory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        UserPermissionManagement management = new UserPermissionManagement();

        // Add a new user
        management.addUser("John Doe", "user");

        // Update the user's role
        management.updateUserRole(1, "admin");

        // Delete a user
        management.deleteUser(1);
    }
}
