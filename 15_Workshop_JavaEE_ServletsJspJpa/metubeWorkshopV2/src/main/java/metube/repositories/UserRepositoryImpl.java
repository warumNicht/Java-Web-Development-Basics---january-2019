package metube.repositories;

import metube.domain.entities.User;
import org.hibernate.jpa.QueryHints;

import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
//        this.entityManager.setProperty("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
    }

    @Override
    public User save(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = this.entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
        return users;
    }

    @Override
    public Optional<User> findById(String id) {
        try {
            User user = this.entityManager.
                    createQuery("SELECT u FROM User u WHERE u.id= :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public long size() {
        Object result = this.entityManager.createQuery("SELECT count(u) FROM User u")
                .getSingleResult();
        return (long)result;
    }

    @Override
    public Optional<User> findByNameAndPassword(String name, String password) {

        try {
            User user = this.entityManager.
                    createQuery("SELECT u FROM User u " +
                            "WHERE u.username= :name AND u.password= :password", User.class)
                    .setParameter("name", name)
                    .setParameter("password", password)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByName(String name) {

        try {
            this.entityManager.getTransaction().begin();

            User user = this.entityManager.
                    createQuery("SELECT u FROM User u " +
                            "WHERE u.username= :name", User.class)
                    .setParameter("name", name)
//                    .setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
                    .getSingleResult();

            this.entityManager.refresh(user);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        } finally {
           this.entityManager.getTransaction().commit();
        }
    }
}
