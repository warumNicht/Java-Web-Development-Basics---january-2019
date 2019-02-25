package application.repositories;

import application.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends GenericRepository<User,String> {

    Optional<User> findByUsername(String username);

    List<User> getNotFriends(String username);

    void update(User user);
}
