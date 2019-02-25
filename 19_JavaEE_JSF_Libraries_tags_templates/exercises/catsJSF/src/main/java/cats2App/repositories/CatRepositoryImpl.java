package cats2App.repositories;

import cats2App.domain.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CatRepositoryImpl implements CatsRepository {
    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cat save(Cat entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Cat> findAll() {
        List<Cat> cats = this.entityManager
                .createQuery("SELECT c FROM Cat c", Cat.class)
                .getResultList();
        return cats;
    }

    @Override
    public List<Cat> getSorted(String criteria) {
        String query="SELECT * FROM cats AS c ORDER BY {criteria}";
        query=query.replace("{criteria}",criteria);

        List<Cat> cats = this.entityManager
                .createNativeQuery(query, Cat.class)
                .getResultList();
        return cats;
    }
}
