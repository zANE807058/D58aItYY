// 代码生成时间: 2025-09-13 01:42:57
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Interceptor;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import java.util.Properties;

/**
 * DatabaseConnectionPoolManager is a utility class responsible for managing the Hibernate connection pool.
 * It provides methods to configure and obtain the SessionFactory instance.
 */
public class DatabaseConnectionPoolManager {

    // The Singleton instance of SessionFactory
    private static SessionFactory sessionFactory;

    /**
     * Private constructor to prevent instantiation.
     */
    private DatabaseConnectionPoolManager() {
    }

    /**
     * Configures the Hibernate connection pool and returns the SessionFactory instance.
     * @return The configured SessionFactory instance.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create the Hibernate Configuration object
                Configuration configuration = new Configuration().configure();
                
                // Create the service registry builder
                BootstrapServiceRegistryBuilder serviceRegistryBuilder = new BootstrapServiceRegistryBuilder();
                Properties settings = new Properties();
                settings.putAll(configuration.getProperties());
                settings.put("hibernate.cache.use_second_level_cache", "true");
                settings.put("hibernate.cache.use_query_cache", "true");
                settings.put("hibernate.generate_statistics", "true");
                settings.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
                
                // Add settings to service registry builder
                StandardServiceRegistryBuilder serviceRegistryBuilderWithSettings = serviceRegistryBuilder.applySettings(settings);
                ServiceRegistry serviceRegistry = serviceRegistryBuilderWithSettings.build();
                
                // Build and configure the SessionFactory
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("Initial SessionFactory creation failed." + ": " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    /**
     * Closes the SessionFactory instance and releases resources.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
