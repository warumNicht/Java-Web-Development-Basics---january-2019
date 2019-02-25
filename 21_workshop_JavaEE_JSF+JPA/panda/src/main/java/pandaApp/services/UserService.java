package pandaApp.services;

import pandaApp.domain.models.serviceModels.UserServiceModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);

    Optional<UserServiceModel> findByUsername(String username);

    List<UserServiceModel> findAll();

    void  update(UserServiceModel userServiceModel);
}
