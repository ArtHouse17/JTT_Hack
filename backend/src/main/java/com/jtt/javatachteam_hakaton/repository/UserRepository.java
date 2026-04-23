package com.jtt.javatachteam_hakaton.repository;

import com.jtt.javatachteam_hakaton.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class UserRepository {
    private final EntityManagerFactory entityManagerFactory;

    public UserRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<User> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery("select u from User u", User.class)
                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Optional<User> findById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(User.class, id));
        } finally {
            entityManager.close();
        }
    }

    public Optional<User> findByUsername(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                .createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
        } finally {
            entityManager.close();
        }
    }

    public User save(User user) {
        return inTransaction(entityManager -> entityManager.merge(user));
    }

    public boolean existsById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Long count = entityManager
                .createQuery("select count(u) from User u where u.id = :id", Long.class)
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
                .createQuery("select count(u) from User u", Long.class)
                .getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public void deleteById(UUID id) {
        inTransaction(entityManager -> {
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
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
