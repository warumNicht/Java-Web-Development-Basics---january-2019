package emplRegisterApp.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E, ID> {
    E save(E entity);

    List<E> findAll();

    Optional<E> findById(ID id);

    void remove(ID id);
}
