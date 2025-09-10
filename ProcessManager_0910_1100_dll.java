// 代码生成时间: 2025-09-10 11:00:54
import org.hibernate.Session;
    import org.hibernate.SessionFactory;
    import org.hibernate.Transaction;
    import org.hibernate.cfg.Configuration;
    import org.hibernate.query.Query;
    import java.util.List;
    import java.util.Properties;

    // ProcessManager类，用于管理进程信息
    public class ProcessManager {
        // 创建SessionFactory实例
        private static final SessionFactory sessionFactory;

        static {
            try {
                // 初始化SessionFactory
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        // 获取SessionFactory实例
        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }

        // 添加进程信息
        public void addProcess(Process process) {
            Session session = null;
            Transaction transaction = null;
            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.save(process);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }

        // 更新进程信息
        public void updateProcess(Process process) {
            Session session = null;
            Transaction transaction = null;
            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.update(process);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }

        // 删除进程信息
        public void deleteProcess(Process process) {
            Session session = null;
            Transaction transaction = null;
            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.delete(process);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }

        // 查询所有进程信息
        public List<Process> getAllProcesses() {
            Session session = null;
            try {
                session = sessionFactory.openSession();
                Query<Process> query = session.createQuery("FROM Process", Process.class);
                return query.getResultList();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }

        // 查询单个进程信息
        public Process getProcessById(Long id) {
            Session session = null;
            try {
                session = sessionFactory.openSession();
                return session.get(Process.class, id);
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
    }

    // 进程实体类
    public class Process {
        private Long id;
        private String name;
        private String status;

        // 省略getter和setter方法

        @Override
        public String toString() {
            return "Process{\"id\":\"" + id + "\", \"name\":\"" + name + "\", \"status\":\"" + status + "\"}";
        }
    }