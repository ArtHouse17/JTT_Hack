package com.jtt.javatachteam_hakaton.repository;

import com.jtt.javatachteam_hakaton.entity.Attempt;
import com.jtt.javatachteam_hakaton.entity.enums.StatusEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class AttemptRepository {
    private final EntityManagerFactory entityManagerFactory;

    public AttemptRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Attempt> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery("select a from Attempt a", Attempt.class)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Optional<Attempt> findById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Attempt.class, id));
        } finally {
            entityManager.close();
        }
    }

    public List<Attempt> findByUserIdAndTaskId(UUID userId, UUID taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery(
                    "select a from Attempt a where a.user.id = :userId and a.task.id = :taskId order by a.startedAt desc",
                    Attempt.class
                )
                .setParameter("userId", userId)
                .setParameter("taskId", taskId)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Optional<Attempt> findLatestByUserIdAndTaskId(UUID userId, UUID taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery(
                    "select a from Attempt a where a.user.id = :userId and a.task.id = :taskId order by a.startedAt desc",
                    Attempt.class
                )
                .setParameter("userId", userId)
                .setParameter("taskId", taskId)
                .setMaxResults(1)
                .getResultStream()
                .findFirst();
        } finally {
            entityManager.close();
        }
    }

    public boolean existsCompletedByUserIdAndTaskId(UUID userId, UUID taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager
                .createQuery(
                    "select count(a) from Attempt a where a.user.id = :userId and a.task.id = :taskId and a.status = :status",
                    Long.class
                )
                .setParameter("userId", userId)
                .setParameter("taskId", taskId)
                .setParameter("status", StatusEnum.COMPLETED)
                .getSingleResult();
            return count > 0;
        } finally {
            entityManager.close();
        }
    }

    public boolean existsSolvedByUserIdAndTaskId(UUID userId, UUID taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager
                .createQuery(
                    """
                    select count(a)
                    from Attempt a
                    where a.user.id = :userId
                      and a.task.id = :taskId
                      and a.status = :status
                      and a.earnedPoints > 0
                    """,
                    Long.class
                )
                .setParameter("userId", userId)
                .setParameter("taskId", taskId)
                .setParameter("status", StatusEnum.COMPLETED)
                .getSingleResult();
            return count > 0;
        } finally {
            entityManager.close();
        }
    }

    public Attempt save(Attempt attempt) {
        return inTransaction(entityManager -> entityManager.merge(attempt));
    }

    public boolean existsById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager
                .createQuery("select count(a) from Attempt a where a.id = :id", Long.class)
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
                .createQuery("select count(a) from Attempt a", Long.class)
                .getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public void deleteById(UUID id) {
        inTransaction(entityManager -> {
            Attempt attempt = entityManager.find(Attempt.class, id);
            if (attempt != null) {
                entityManager.remove(attempt);
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
