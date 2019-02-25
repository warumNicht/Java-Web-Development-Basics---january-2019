package chushkaApp.repository;

import java.util.List;

public interface GenericRepository<E,K> {
    E save(E entity);

    E findBy(K id);

    List<E> findAll();
}
