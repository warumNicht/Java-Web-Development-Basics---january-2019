package application.services;

import application.domain.entities.Channel;
import application.domain.entities.User;
import application.domain.entities.enums.UserRole;
import application.domain.models.serviceModels.ChannelServiceModel;
import application.domain.models.serviceModels.UserServiceModel;
import application.repositories.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        try {
            User user = this.modelMapper.map(userServiceModel, User.class);
            Long count = this.userRepository.count();
            if(count!=0){
                user.setRole(UserRole.User);
            }else {
                user.setRole(UserRole.Admin);
            }
            this.userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<UserServiceModel> findAll() {
        List<UserServiceModel> users = this.userRepository.findAll().stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public UserServiceModel checkExistentUser(String username, String password) {
        User user = this.userRepository.existsUser(username, password);
        if(user==null){
            return null;
        }
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        return userServiceModel;
    }

    @Override
    public void addChannelToUser(String username, ChannelServiceModel channelServiceModel) {
        User user = this.userRepository.findByName(username).orElse(null);
        Channel channel = this.modelMapper.map(channelServiceModel, Channel.class);
        user.getFollowedChannels().add(channel);
        this.userRepository.updateUser(user);
    }
    @Override
    public void removeChannel(String username, ChannelServiceModel channelById) {
        User user = this.userRepository.findByName(username).orElse(null);
        Channel channel = this.modelMapper.map(channelById, Channel.class);
        Set<Channel> channels = user.getFollowedChannels().stream()
                .filter(c -> !c.getId().equals(channel.getId()))
                .collect(Collectors.toSet());
        user.setFollowedChannels(channels);
        this.userRepository.updateUser(user);
    }

    @Override
    public boolean noUsers() {
        return this.userRepository.findAll().size()==0;
    }

    @Override
    public void updateUser(String username) {
        User user = this.userRepository.findByName(username).orElse(null);
        this.userRepository.updateUser(user);
    }

    @Override
    public List<ChannelServiceModel> getUsersChannels(String username) {
        User user = this.userRepository.findByName(username).orElse(null);
        Set<Channel> followedChannels = user.getFollowedChannels();
        return followedChannels.stream()
                .map(ch->this.modelMapper.map(ch,ChannelServiceModel.class))
                .collect(Collectors.toList());
    }


}
