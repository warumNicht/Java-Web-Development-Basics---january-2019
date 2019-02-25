package application.services;

import application.domain.models.serviceModels.UserServiceModel;

public interface UserService {

    void userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);


}
