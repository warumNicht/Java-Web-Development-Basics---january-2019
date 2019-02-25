package application.repositories;

import application.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User,String> {

    Optional<User> findByName(String name);

    User existsUser(String username, String password);

    void updateUser(User user);
}
