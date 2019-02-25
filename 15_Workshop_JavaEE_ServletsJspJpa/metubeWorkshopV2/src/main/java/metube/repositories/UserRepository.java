package metube.repositories;

import metube.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User,String> {
    Optional<User> findByNameAndPassword(String name, String password);

    Optional<User> findByName(String name);
}
