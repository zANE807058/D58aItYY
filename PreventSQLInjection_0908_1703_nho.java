// 代码生成时间: 2025-09-08 17:03:07
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

// 定义一个简单的实体类 User
class User {
    private int id;
    private String name;
    private String email;
    // 省略构造函数、getter 和 setter 方法
}

public class PreventSQLInjection {

    // 获取SessionFactory对象，用于创建Session
    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 使用Hibernate框架执行查询，防止SQL注入
    public void listUsers() {
        Session session = null;
        Transaction tx = null;
        try {
            // 开启Session
            session = sessionFactory.openSession();
            // 开启事务
            tx = session.beginTransaction();

            // 创建查询，使用命名查询或HQL/Criteria API
            Query<User> query = session.createQuery("FROM User WHERE name = :name", User.class);
            query.setParameter("name", "John Doe"); // 安全地设置参数值，防止SQL注入

            // 执行查询并获取结果
            List<User> users = query.list();
            for (User user : users) {
                System.out.println(user.getName() + " - " + user.getEmail());
            }

            // 提交事务
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // 出错时回滚事务
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // 关闭Session
            }
        }
    }

    public static void main(String[] args) {
        PreventSQLInjection app = new PreventSQLInjection();
        app.listUsers();
    }
}
