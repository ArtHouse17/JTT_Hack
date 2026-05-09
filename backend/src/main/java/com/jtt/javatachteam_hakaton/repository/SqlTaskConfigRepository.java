package com.jtt.javatachteam_hakaton.repository;

import com.jtt.javatachteam_hakaton.entity.SqlTaskConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Optional;
import java.util.UUID;

public class SqlTaskConfigRepository {
    private final EntityManagerFactory entityManagerFactory;

    public SqlTaskConfigRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Optional<SqlTaskConfig> findByTaskId(UUID taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(SqlTaskConfig.class, taskId));
        } finally {
            entityManager.close();
        }
    }
}
