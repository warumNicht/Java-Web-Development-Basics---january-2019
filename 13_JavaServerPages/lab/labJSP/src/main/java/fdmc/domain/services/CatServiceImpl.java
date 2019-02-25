package fdmc.domain.services;

import fdmc.domain.entities.Cat;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CatServiceImpl implements CatService {
    private final EntityManager entityManager;

    public CatServiceImpl() {
        this.entityManager=Persistence.createEntityManagerFactory("cats")
                .createEntityManager();
    }

    @Override
    public List<Cat> getAll() {
        List<Cat> resultList = this.entityManager.
                createQuery("SELECT c FROM Cat c", Cat.class).getResultList();
        return resultList;
    }

    @Override
    public void addCat(Cat cat) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(cat);
        this.entityManager.getTransaction().commit();
    }
}
