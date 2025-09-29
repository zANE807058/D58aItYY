// 代码生成时间: 2025-09-29 16:35:36
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerManagement {

    // Method to create a new customer
    public void addCustomer(Customer customer) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession();
             Transaction transaction = session.beginTransaction()) {
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get all customers
    public List<Customer> getAllCustomers() {
        List<Customer> customers = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()) {
            Query<Customer> query = session.createQuery("from Customer", Customer.class);
            customers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Method to update an existing customer
    public void updateCustomer(Customer customer) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession();
             Transaction transaction = session.beginTransaction()) {
            session.update(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a customer
    public void deleteCustomer(int customerId) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession();
             Transaction transaction = session.beginTransaction()) {
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null) {
                session.delete(customer);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        CustomerManagement cm = new CustomerManagement();
        // Add a new customer
        cm.addCustomer(new Customer(1, "John Doe", "john@example.com"));
        // Get all customers
        List<Customer> customers = cm.getAllCustomers();
        if (customers != null) {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
        // Update an existing customer
        Customer customerToUpdate = new Customer(1, "Johnathan Doe", "johnathan@example.com");
        cm.updateCustomer(customerToUpdate);
        // Delete a customer
        cm.deleteCustomer(1);
    }
}

/**
 * Customer.java
 * 
 * This class represents a Customer entity.
 */
public class Customer {
    private int id;
    private String name;
    private String email;

    public Customer() {
    }

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
