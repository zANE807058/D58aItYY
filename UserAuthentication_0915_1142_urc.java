// 代码生成时间: 2025-09-15 11:42:03
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Properties;

// User class represents a user entity with username and password attributes
class User {
    private String username;
    private String password;

    // Constructor, getters and setters
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// AuthenticationService class handles user authentication
class AuthenticationService {
    private SessionFactory sessionFactory;

    public AuthenticationService() {
        // Configure and build the SessionFactory
        Configuration configuration = new Configuration().configure();
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/your_database");
        properties.setProperty("hibernate.connection.username", "your_username");
        properties.setProperty("hibernate.connection.password", "your_password");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        sessionFactory = configuration.buildSessionFactory(properties);
    }

    // Method to authenticate a user
    public boolean authenticateUser(String username, String password) {
        boolean authenticated = false;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                // Query to find a user by username
                User user = session.get(User.class, username);

                if (user != null && user.getPassword().equals(password)) {
                    authenticated = true;
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                System.err.println("Error during authentication: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error opening session: " + e.getMessage());
        }
        return authenticated;
    }
}

// Main class to test user authentication
public class UserAuthentication {
    public static void main(String[] args) {
        AuthenticationService authService = new AuthenticationService();
        String username = "test_user"; // Replace with actual username
        String password = "test_password"; // Replace with actual password

        boolean isAuthenticated = authService.authenticateUser(username, password);

        if (isAuthenticated) {
            System.out.println("User authenticated successfully");
        } else {
            System.out.println("Authentication failed");
        }
    }
}