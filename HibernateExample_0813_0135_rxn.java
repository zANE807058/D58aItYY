// 代码生成时间: 2025-08-13 01:35:40
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateExample {

    // The main method to run the example.
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                // Create a new entity and save it to the database
                User newUser = new User(1L, "John Doe", "john@example.com");
                session.save(newUser);
                transaction.commit();

                // Query the database to retrieve the saved entity
                User retrievedUser = session.get(User.class, 1L);
                System.out.println("Retrieved user: " + retrievedUser);
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * User.java
 * Represents a user entity with attributes id, name, and email.
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public User() {
        // Default constructor for Hibernate
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and setters for entity attributes
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}