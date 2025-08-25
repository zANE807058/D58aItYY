// 代码生成时间: 2025-08-26 02:31:36
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import java.util.Date;

// 测试数据生成器类
public class TestDataGenerator {

    private static SessionFactory sessionFactory;

    static {
        // 创建ServiceRegistry
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // 载入并应用hibernate配置文件的配置
                .build();
        try {
            // 创建Metadata
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

            // 创建SessionFactory
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
            // Log the problem
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static Session getSession() throws Exception {
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {
        try {
            // 获取Session
            Session session = getSession();

            // 开始事务
            Transaction transaction = session.beginTransaction();

            // 这里添加生成测试数据的代码
            // 例如，生成10个用户数据
            for (int i = 1; i <= 10; i++) {
                // 假设有一个User实体类
                User user = new User();
                user.setName("User" + i);
                user.setEmail("user" + i + "@example.com");
                user.setCreationDate(new Date());
                session.save(user);
            }

            // 提交事务
            transaction.commit();

            System.out.println("Test data generated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in generating test data.");
        } finally {
            sessionFactory.close();
        }
    }
}

// 假设的User实体类
class User {
    private Long id;
    private String name;
    private String email;
    private Date creationDate;

    // 省略构造函数、getter和setter方法
}