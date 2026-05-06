package com.jtt.javatachteam_hakaton.repository;

import com.jtt.javatachteam_hakaton.entity.Task;
import com.jtt.javatachteam_hakaton.entity.enums.TaskTypeEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class TaskRepository {
    private final EntityManagerFactory entityManagerFactory;

    public TaskRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Task> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery("select t from Task t", Task.class)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Optional<Task> findById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Task.class, id));
        } finally {
            entityManager.close();
        }
    }

    public List<Task> findByTaskType(TaskTypeEnum taskType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery(
                    "select t from Task t where t.taskType = :taskType order by t.title asc",
                    Task.class
                )
                .setParameter("taskType", taskType)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Task save(Task task) {
        return inTransaction(entityManager -> entityManager.merge(task));
    }

    public boolean existsById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager
                .createQuery("select count(t) from Task t where t.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
            return count > 0;
        } finally {
            entityManager.close();
        }
    }

    public long countByTaskType(TaskTypeEnum taskType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery("select count(t) from Task t where t.taskType = :taskType", Long.class)
                .setParameter("taskType", taskType)
                .getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public int sumMaxPointsByTaskType(TaskTypeEnum taskType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long result = entityManager
                .createQuery("select coalesce(sum(t.maxPoints), 0) from Task t where t.taskType = :taskType", Long.class)
                .setParameter("taskType", taskType)
                .getSingleResult();
            return result == null ? 0 : result.intValue();
        } finally {
            entityManager.close();
        }
    }

    public long count() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery("select count(t) from Task t", Long.class)
                .getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public void deleteById(UUID id) {
        inTransaction(entityManager -> {
            Task task = entityManager.find(Task.class, id);
            if (task != null) {
                entityManager.remove(task);
            }
            return null;
        });
    }

    private <T> T inTransaction(Function<EntityManager, T> action) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T result = action.apply(entityManager);
            transaction.commit();
            return result;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }
}
