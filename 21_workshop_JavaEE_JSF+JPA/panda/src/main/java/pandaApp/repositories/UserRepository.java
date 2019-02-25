package pandaApp.repositories;

import pandaApp.domain.entities.User;

import java.util.Optional;


public interface UserRepository extends GenericRepository<User,String> {

    Optional<User> findByUsername(String username);

    void update(User user);
}
