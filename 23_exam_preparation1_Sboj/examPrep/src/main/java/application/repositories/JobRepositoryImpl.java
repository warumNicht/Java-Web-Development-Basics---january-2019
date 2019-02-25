package application.repositories;

import application.domain.entities.Job;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JobRepositoryImpl implements JobRepository {
    private final EntityManager entityManager;

    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Job save(Job entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Optional<Job> findById(String id) {
        try {
            Job user = this.entityManager
                    .createQuery("SELECT j FROM Job j WHERE j.id= :id", Job.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Job> findAll() {
        return this.entityManager.createQuery("SELECT j FROM Job j", Job.class)
                .getResultList();
    }

    @Override
    public Long size() {
        return null;
    }

    @Override
    public void deleteById(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("DELETE FROM Job j WHERE j.id= :id")
                .setParameter("id",id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
