// 代码生成时间: 2025-08-29 17:12:40
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.selector.spi.StrategySelector;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToWriter;
import org.hibernate.internal.util.config.ZonedDateTimeFormat;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Properties;

public class DatabaseMigrationTool {

    private static StandardServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;
    private static final String MIGRATION_SCRIPT_FILENAME = "migration_script.sql";

    // 方法：初始化Hibernate配置和SessionFactory
    private static void initializeHibernate() {
        // 加载配置文件
        Configuration configuration = new Configuration().configure();
        // 创建服务注册表
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    // 方法：关闭SessionFactory和ServiceRegistry
    private static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

    // 方法：执行数据库迁移
    public static void migrateDatabase() {
        try {
            initializeHibernate();
            // 创建SchemaExport对象
            SchemaExport export = new SchemaExport((SessionFactoryImplementor) sessionFactory);
            // 设置数据库方言
            export.setHbm2ddlAuto(sessionFactory.getDialect());
            // 设置输出文件
            String timestamp = ZonedDateTime.now().format(ZonedDateTimeFormat.INSTANCE);
            String scriptPath = MIGRATION_SCRIPT_FILENAME + "_" + timestamp + ".sql";
            try (FileWriter fileWriter = new FileWriter(scriptPath)) {
                // 导出数据库迁移脚本到文件
                export.create(true, true, fileWriter);
                System.out.println("Database migration script created successfully at: " + scriptPath);
            } catch (IOException e) {
                System.err.println("Error writing migration script: " + e.getMessage());
            }
            shutdown();
        } catch (Exception e) {
            System.err.println("Error during database migration: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        migrateDatabase();
    }
}
