// 代码生成时间: 2025-09-06 07:44:13
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

// 进程实体类
@Entity
@Table(name = "processes")
public class Process {
    @Id
    private int id;
    private String name;
    private String status;

    public Process() {
    }

    public Process(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

public class ProcessManager {
    private SessionFactory sessionFactory;

    public ProcessManager() {
        // 初始化SessionFactory
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // 开始一个新事务
    public void startTransaction() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // 执行业务逻辑
            // ...
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    // 添加一个新进程
    public void addProcess(Process process) {
        startTransaction();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.save(process);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    // 获取所有进程
    public List<Process> getAllProcesses() {
        List<Process> processes = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Process> query = cb.createQuery(Process.class);
            Root<Process> process = query.from(Process.class);
            query.select(process);
            processes = session.createQuery(query).getResultList();
            return processes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 更新进程状态
    public void updateProcessStatus(int id, String newStatus) {
        startTransaction();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Process process = session.get(Process.class, id);
            if (process != null) {
                process.setStatus(newStatus);
                session.update(process);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    // 删除进程
    public void deleteProcess(int id) {
        startTransaction();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Process process = session.get(Process.class, id);
            if (process != null) {
                session.delete(process);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    // 关闭SessionFactory
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
