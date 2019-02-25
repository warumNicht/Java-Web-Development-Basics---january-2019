package metube.services;

import metube.domain.models.service.UserServiceModel;

public interface UserService {
    boolean registerUser(UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByName(String username);
}
