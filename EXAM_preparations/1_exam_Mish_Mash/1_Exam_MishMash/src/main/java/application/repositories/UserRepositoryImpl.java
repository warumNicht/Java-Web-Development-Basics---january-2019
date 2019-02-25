package application.repositories;

import application.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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
        try{

            User user = this.entityManager.createQuery("SELECT u FROM User u " +
                    "WHERE u.id= :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.of(user);
        }catch (Exception e){
            return Optional.empty();
        }
    }
    @Override
    public Optional<User> findByName(String name) {
        try{

            EntityManager em = Persistence.createEntityManagerFactory("exam")
                    .createEntityManager();

            User user = em.createQuery("SELECT u FROM User u " +
                    "WHERE u.username= :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();


//            this.entityManager.refresh(user);
//            user.getFollowedChannels().stream()
//                    .forEach(c->this.entityManager.refresh(c));
            em.clear();
            return Optional.of(user);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("SELECT u FROM User u",User.class)
                .getResultList();
    }

    @Override
    public Long count() {
        Long singleResult = this.entityManager.createQuery("SELECT count(u) FROM User AS u", Long.class)
                .getSingleResult();
        return singleResult;
    }

    @Override
    public User existsUser(String username, String password) {
        try {
           return this.entityManager.createQuery("SELECT u FROM User u " +
                    "WHERE u.username= :username AND u.password= :password",User.class)
                    .setParameter("username",username)
                    .setParameter("password",password)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();
    }
}
