// 代码生成时间: 2025-08-04 00:30:30
package com.example.urlvalidator;

import java.net.URL;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;

@Entity
public class UrlValidatorService {
    @Id
    private String url;
    private boolean isValid;
    
    /**
     * Default constructor for UrlValidatorService.
     */
    public UrlValidatorService() {
        // Default constructor
    }
    
    /**
     * Validates a given URL for its validity.
     *
     * @param url The URL to be validated.
     * @return Whether the URL is valid or not.
     */
    public boolean validateURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Gets the URL field.
     *
     * @return The URL as a String.
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * Sets the URL field.
     *
     * @param url The URL to be set.
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * Gets the validity of the URL.
     *
     * @return Whether the URL is valid.
     */
    public boolean getIsValid() {
        return isValid;
    }
    
    /**
     * Sets the validity of the URL.
     *
     * @param isValid The validity to be set.
     */
    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
    
    /**
     * Main method to run the URL validation service.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            
            UrlValidatorService urlValidatorService = new UrlValidatorService();
            urlValidatorService.setUrl("http://www.example.com");
            urlValidatorService.setIsValid(urlValidatorService.validateURL(urlValidatorService.getUrl()));
            
            transaction.commit();
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
