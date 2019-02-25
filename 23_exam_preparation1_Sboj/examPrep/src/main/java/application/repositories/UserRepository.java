package application.repositories;

import application.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User,String> {

    Optional<User> findByUsername(String username);
}
