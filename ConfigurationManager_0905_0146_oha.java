// 代码生成时间: 2025-09-05 01:46:34
package com.example.configuration;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.Entity;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * ConfigurationManager is a utility class that handles Hibernate configuration.
 * It provides a centralized way to manage database configurations.
 */
public class ConfigurationManager {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);
    private Properties properties;
    private Configuration configuration;

    /**
     * Private constructor to prevent instantiation.
     */
    private ConfigurationManager() {
        properties = new Properties();
        configuration = new Configuration();
    }

    /**
     * Returns the single instance of ConfigurationManager.
     * @return the single instance of ConfigurationManager.
     */
    public static ConfigurationManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Loads the Hibernate configuration from the specified file.
     * @param configFilename The name of the configuration file.
     */
    public void loadConfiguration(String configFilename) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFilename)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Configuration file not found: " + configFilename);
            }
            properties.load(inputStream);
            configuration.configure(); // Auto-configure based on properties
        } catch (Exception e) {
            logger.error("Error loading configuration from file: " + configFilename, e);
            throw new RuntimeException("Failed to load configuration.", e);
        }
    }

    /**
     * Adds an entity class to the configuration.
     * @param entityClass The entity class to add.
     */
    public void addEntityClass(Class<?> entityClass) {
        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Class is not annotated with @Entity: " + entityClass.getName());
        }
        configuration.addAnnotatedClass(entityClass);
    }

    /**
     * Returns the set of entity classes configured in the application.
     * @return the set of entity classes.
     */
    public Set<Class<?>> getEntityClasses() {
        return configuration.getClasses();
    }

    /**
     * Returns the Hibernate Configuration instance.
     * @return the Hibernate Configuration instance.
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * SingletonHolder is a private static class to hold the singleton instance.
     */
    private static class SingletonHolder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }
}
