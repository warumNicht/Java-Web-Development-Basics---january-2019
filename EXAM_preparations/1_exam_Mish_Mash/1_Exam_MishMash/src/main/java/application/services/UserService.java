package application.services;

import application.domain.models.serviceModels.ChannelServiceModel;
import application.domain.models.serviceModels.UserServiceModel;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean registerUser(UserServiceModel userServiceModel);

    List<UserServiceModel> findAll();

    UserServiceModel checkExistentUser(String username, String password);

    void addChannelToUser(String username, ChannelServiceModel channelServiceModel);


    List<ChannelServiceModel> getUsersChannels(String username);

    void removeChannel(String username, ChannelServiceModel channelById);

    boolean noUsers();

    void updateUser(String username);
}
