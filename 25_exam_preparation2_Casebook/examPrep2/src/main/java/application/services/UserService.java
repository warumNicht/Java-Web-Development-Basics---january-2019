package application.services;

import application.domain.models.serviceModels.UserServiceModel;

import java.util.List;

public interface UserService {

    void userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    UserServiceModel findById(String id);

    List<UserServiceModel> getNotFriends(String username);

    void update(UserServiceModel userServiceModel);


}
