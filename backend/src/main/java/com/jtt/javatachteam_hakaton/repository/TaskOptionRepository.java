package com.jtt.javatachteam_hakaton.repository;

import com.jtt.javatachteam_hakaton.entity.TaskOption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class TaskOptionRepository {
    private final EntityManagerFactory entityManagerFactory;

    public TaskOptionRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<TaskOption> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery("select to from TaskOption to", TaskOption.class)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Optional<TaskOption> findById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(TaskOption.class, id));
        } finally {
            entityManager.close();
        }
    }

    public List<TaskOption> findByTaskId(UUID taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery(
                    "select to from TaskOption to where to.task.id = :taskId order by to.sortOrder asc, to.id asc",
                    TaskOption.class
                )
                .setParameter("taskId", taskId)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<TaskOption> findCorrectByTaskId(UUID taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery(
                    "select to from TaskOption to where to.task.id = :taskId and to.isCorrect = true order by to.sortOrder asc, to.id asc",
                    TaskOption.class
                )
                .setParameter("taskId", taskId)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public TaskOption save(TaskOption taskOption) {
        return inTransaction(entityManager -> entityManager.merge(taskOption));
    }

    public boolean existsById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager
                .createQuery("select count(to) from TaskOption to where to.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
            return count > 0;
        } finally {
            entityManager.close();
        }
    }

    public long count() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery("select count(to) from TaskOption to", Long.class)
                .getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public void deleteById(UUID id) {
        inTransaction(entityManager -> {
            TaskOption taskOption = entityManager.find(TaskOption.class, id);
            if (taskOption != null) {
                entityManager.remove(taskOption);
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
