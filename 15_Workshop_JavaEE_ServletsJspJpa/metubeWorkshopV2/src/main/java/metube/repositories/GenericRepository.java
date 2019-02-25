package metube.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<Entity,Key> {
    Entity save(Entity entity);

    Entity update(Entity entity);

    List<Entity> findAll();

    Optional<Entity> findById(Key id);

    long size();
}
