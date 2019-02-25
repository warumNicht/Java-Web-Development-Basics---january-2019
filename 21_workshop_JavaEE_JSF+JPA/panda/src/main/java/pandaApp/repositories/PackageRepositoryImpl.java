package pandaApp.repositories;

import pandaApp.domain.entities.Package;
import pandaApp.domain.entities.enums.Status;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PackageRepositoryImpl implements PackageRepository {
    private final EntityManager entityManager;

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Package save(Package entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Optional<Package> findById(String id) {
        try {
            Package aPackage = this.entityManager
                    .createQuery("SELECT p FROM Package p WHERE p.id= :id", Package.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(aPackage);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Package> findAll() {
        return this.entityManager.createQuery("SELECT p FROM Package p", Package.class)
                .getResultList();
    }

    @Override
    public Long size() {
        Long size = this.entityManager
                .createQuery("SELECT count(p) FROM Package p", Long.class)
                .getSingleResult();
        return size;
    }

    @Override
    public List<Package> findAllByStatus(Status status) {
        List<Package> packages = this.entityManager.createQuery("SELECT p FROM Package p " +
                "WHERE p.status= :status", Package.class)
                .setParameter("status", status)
                .getResultList();
        return packages;
    }

    @Override
    public void updatePackage(Package aPackage) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(aPackage);
        this.entityManager.getTransaction().commit();
    }
}
