// 代码生成时间: 2025-08-15 07:19:01
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// 定义一个简单的实体类，用于演示Hibernate操作
class User {
    private int id;
    private String name;

    public User() {}

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}

// HTTP请求处理器
@RestController
public class HttpRequestHandler {

    private SessionFactory sessionFactory;

    public HttpRequestHandler() {
        // 初始化SessionFactory
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // GET请求处理器
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request, HttpServletResponse response) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            List<User> users = new ArrayList<>();
            users.addAll(session.createQuery("from User", User.class).list());

            transaction.commit();
            session.close();

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST请求处理器
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.save(user);
            transaction.commit();
            session.close();

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
