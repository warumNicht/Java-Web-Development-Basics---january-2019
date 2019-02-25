package application.repositories;

import application.domain.entities.Email;
import application.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class EmailRepositoryImpl implements EmailRepository {
    private final EntityManager entityManager;

    @Inject
    public EmailRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Email save(Email entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Optional<Email> findById(String id) {
        try {
            Email email = this.entityManager
                    .createQuery("SELECT e FROM Email e WHERE e.id= :id", Email.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Email> findAll() {
        return null;
    }

    @Override
    public Long size() {
        return null;
    }

    @Override
    public void update(Email entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();
    }
}
