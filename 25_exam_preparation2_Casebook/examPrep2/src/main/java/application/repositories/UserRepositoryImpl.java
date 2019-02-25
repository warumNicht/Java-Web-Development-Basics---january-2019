package application.repositories;

import application.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Optional<User> findById(String id) {
        try {
            User user = this.entityManager
                    .createQuery("SELECT u FROM User u WHERE u.id= :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            User user = this.entityManager
                    .createQuery("SELECT u FROM User u WHERE u.username= :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public Long size() {
        Long size = this.entityManager
                .createQuery("SELECT count(u) FROM User u", Long.class)
                .getSingleResult();
        return size;
    }

    @Override
    public List<User> getNotFriends(String username) {

        List<User> users = this.entityManager.createQuery("SELECT u FROM User AS u " +
                "WHERE u not in(SELECT f FROM User AS u2 " +
                "JOIN u2.friends AS f " +
                "WHERE u2.username = :username) " +
                "AND u.username not like :username" , User.class)
                .setParameter("username", username)
                .getResultList();

        String query="SELECT * FROM users AS u \n" +
                "WHERE u.id not in\n" +
                "(SELECT u2.friend_id from users_friends AS u2 \n" +
                "WHERE u2.user_id='{id}')" +
                "AND u.id!='{id}'";
        query=query.replace("{id}", username);

//
//        List<User> users2 = this.entityManager.createNativeQuery(query, User.class)
//                .getResultList();

        return users;
    }

    @Override
    public void update(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();
    }
}
