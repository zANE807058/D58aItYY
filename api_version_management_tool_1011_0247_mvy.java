// 代码生成时间: 2025-10-11 02:47:32
package com.api.version.tool;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// DTO to represent API version
class ApiVersionDTO {
    private int id;
    private String version;
    private String description;

    // Constructor, getters and setters
    public ApiVersionDTO() {}

    public ApiVersionDTO(int id, String version, String description) {
        this.id = id;
        this.version = version;
        this.description = description;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

// Repository interface for API version
interface ApiVersionRepository {
    List<ApiVersionDTO> getAllApiVersions();
    void addApiVersion(ApiVersionDTO apiVersion);
    void updateApiVersion(ApiVersionDTO apiVersion);
    void deleteApiVersion(int id);
}

// Implementation of the repository using Hibernate
class ApiVersionRepositoryImpl implements ApiVersionRepository {
    private SessionFactory sessionFactory;

    public ApiVersionRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<ApiVersionDTO> getAllApiVersions() {
        Session session = getSession();
        List<ApiVersionDTO> apiVersions = new ArrayList<>();
        try {
            apiVersions = session.createQuery("FROM ApiVersion", ApiVersionDTO.class).getResultList();
        } finally {
            session.close();
        }
        return apiVersions;
    }

    @Override
    public void addApiVersion(ApiVersionDTO apiVersion) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(apiVersion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateApiVersion(ApiVersionDTO apiVersion) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(apiVersion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteApiVersion(int id) {
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ApiVersionDTO apiVersion = session.get(ApiVersionDTO.class, id);
            if (apiVersion != null) {
                session.delete(apiVersion);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

// Service interface for API version
interface ApiVersionService {
    List<ApiVersionDTO> fetchAllApiVersions();
    ApiVersionDTO addApiVersion(String version, String description);
    ApiVersionDTO updateApiVersion(int id, String version, String description);
    boolean deleteApiVersion(int id);
}

// Implementation of the service
class ApiVersionServiceImpl implements ApiVersionService {
    private ApiVersionRepository apiVersionRepository;

    public ApiVersionServiceImpl(ApiVersionRepository repository) {
        this.apiVersionRepository = repository;
    }

    @Override
    public List<ApiVersionDTO> fetchAllApiVersions() {
        return apiVersionRepository.getAllApiVersions();
    }

    @Override
    public ApiVersionDTO addApiVersion(String version, String description) {
        ApiVersionDTO apiVersion = new ApiVersionDTO(0, version, description);
        apiVersionRepository.addApiVersion(apiVersion);
        return apiVersion;
    }

    @Override
    public ApiVersionDTO updateApiVersion(int id, String version, String description) {
        ApiVersionDTO apiVersion = new ApiVersionDTO(id, version, description);
        apiVersionRepository.updateApiVersion(apiVersion);
        return apiVersion;
    }

    @Override
    public boolean deleteApiVersion(int id) {
        apiVersionRepository.deleteApiVersion(id);
        return true;
    }
}

// Main class to run the API version management tool
public class ApiVersionManagementTool {
    public static void main(String[] args) {
        ApiVersionRepository apiVersionRepository = new ApiVersionRepositoryImpl();
        ApiVersionService apiVersionService = new ApiVersionServiceImpl(apiVersionRepository);

        // Example usage
        try {
            ApiVersionDTO newVersion = apiVersionService.addApiVersion("1.0.0", "First version of the API");
            System.out.println("Added new API version ID: " + newVersion.getId());

            ApiVersionDTO updatedVersion = apiVersionService.updateApiVersion(newVersion.getId(), "1.0.1", "Updated version of the API");
            System.out.println("Updated API version ID: " + updatedVersion.getId());

            boolean deleted = apiVersionService.deleteApiVersion(updatedVersion.getId());
            System.out.println("Deleted API version: " + (deleted ? "Success" : "Failed"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}