package com.jtt.javatachteam_hakaton.repository;

import com.jtt.javatachteam_hakaton.entity.AttemptAnswer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class AttemptAnswerRepository {
    private final EntityManagerFactory entityManagerFactory;

    public AttemptAnswerRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<AttemptAnswer> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery("select aa from AttemptAnswer aa", AttemptAnswer.class)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Optional<AttemptAnswer> findById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(AttemptAnswer.class, id));
        } finally {
            entityManager.close();
        }
    }

    public List<AttemptAnswer> findByAttemptId(UUID attemptId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery(
                    "select aa from AttemptAnswer aa where aa.attempt.id = :attemptId",
                    AttemptAnswer.class
                )
                .setParameter("attemptId", attemptId)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public AttemptAnswer save(AttemptAnswer attemptAnswer) {
        return inTransaction(entityManager -> entityManager.merge(attemptAnswer));
    }

    public List<AttemptAnswer> saveAll(List<AttemptAnswer> attemptAnswers) {
        return inTransaction(entityManager -> {
            for (int index = 0; index < attemptAnswers.size(); index++) {
                AttemptAnswer merged = entityManager.merge(attemptAnswers.get(index));
                attemptAnswers.set(index, merged);
            }
            return attemptAnswers;
        });
    }

    public boolean existsById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager
                .createQuery("select count(aa) from AttemptAnswer aa where aa.id = :id", Long.class)
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
                .createQuery("select count(aa) from AttemptAnswer aa", Long.class)
                .getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public void deleteById(UUID id) {
        inTransaction(entityManager -> {
            AttemptAnswer attemptAnswer = entityManager.find(AttemptAnswer.class, id);
            if (attemptAnswer != null) {
                entityManager.remove(attemptAnswer);
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
