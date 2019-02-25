package cats2App.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<E, ID> {
    E save(E entity);

    List<E> findAll();

}
