package metube.repositories;

import metube.domain.entities.Tube;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {
    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager=Persistence
                .createEntityManagerFactory("metube")
                .createEntityManager();
    }

    @Override
    public Optional<Tube> findByName(String name) {
        try {
            Tube tube = this.entityManager.createQuery("SELECT t FROM Tube t WHERE t.name=:name", Tube.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(tube);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
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
            Tube tube = this.entityManager.createQuery("SELECT t FROM Tube t WHERE t.id=:id", Tube.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(tube);
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
