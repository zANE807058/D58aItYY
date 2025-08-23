// 代码生成时间: 2025-08-24 03:22:06
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Properties;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.SessionBuilder;

// 用户实体类
class User {
    private int id;
    private String username;
    private String password;

    // 省略构造函数、getter和setter方法
}

// 用户登录验证服务类
public class UserLoginSystem {

    private SessionFactory sessionFactory;

    public UserLoginSystem() {
        // 初始化SessionFactory
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // 用户登录验证方法
    public boolean validateUser(String username, String password) {
        Transaction transaction = null;
        boolean isValidUser = false;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // 使用HQL查询语句查找用户
            Query<User> query = session.createQuery("FROM User WHERE username = :username AND password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<User> users = query.list();
            if (!users.isEmpty()) {
                isValidUser = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return isValidUser;
    }

    // 关闭SessionFactory
    public void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        UserLoginSystem system = new UserLoginSystem();
        boolean isValid = system.validateUser("admin", "password123");
        System.out.println("User is valid: " + isValid);
        system.closeSessionFactory();
    }
}
