package metube.repositories;

import metube.domain.entities.Tube;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {
    private  EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public Tube update(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(tube);
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public List<Tube> findAll() {

        List<Tube> tubes = this.entityManager.createQuery("SELECT t FROM Tube t", Tube.class)
                .getResultList();

        return tubes;
    }

    @Override
    public Optional<Tube> findById(String id) {
        try {

            Tube tube = this.entityManager.
                    createQuery("SELECT t FROM Tube t WHERE t.id= :id", Tube.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(tube);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public long size() {
        Long count = this.entityManager.createQuery("SELECT COUNT(t) FROM Tube t", long.class)
                .getSingleResult();
        return count;
    }

    @Override
    public List<Tube> findAllPending() {
        List<Tube> tubes = this.entityManager.
                createQuery("SELECT t FROM Tube t " +
                        "WHERE t.status='Pending'", Tube.class)
                .getResultList();

        return tubes;
    }
    @Override
    public List<Tube> findAllApproved() {
        List<Tube> tubes = this.entityManager.
                createQuery("SELECT t FROM Tube t " +
                        "WHERE t.status='Approved'", Tube.class)
                .getResultList();

        return tubes;
    }
}
