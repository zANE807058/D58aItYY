// 代码生成时间: 2025-10-02 23:25:44
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;

public class DateTimePicker {

    // Hibernate Session Factory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + "
" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(String[] args) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                // Start a transaction
                transaction = session.beginTransaction();

                // Here you would implement your date-time selection logic
                // For demonstration, we are just printing a current date-time
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = dateFormat.format(new Date());
                System.out.println("Current Date and Time: " + currentTime);

                // Commit the transaction
                transaction.commit();
            } catch (RuntimeException e) {
                if (null != transaction) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    // Hibernate Session Factory cleanup, called at the end of the program
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
