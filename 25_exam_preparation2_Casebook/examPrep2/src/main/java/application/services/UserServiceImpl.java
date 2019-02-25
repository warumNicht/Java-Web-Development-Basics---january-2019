package application.services;

import application.domain.entities.User;
import application.domain.models.serviceModels.UserServiceModel;
import application.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
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
    public void userRegister(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        String hashedPassword = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(hashedPassword);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel userLogin(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername()).orElse(null);
        String hashedPassword = DigestUtils.sha256Hex(userServiceModel.getPassword());

        if(user==null|| !user.getPassword().equals(hashedPassword)){
            return null;
        }
        UserServiceModel userServiceModel1 = this.modelMapper.map(user, UserServiceModel.class);
        return userServiceModel;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        return this.modelMapper.map(user,UserServiceModel.class);
    }

    @Override
    public UserServiceModel findById(String id) {
        User user = this.userRepository.findById(id).orElse(null);
        return this.modelMapper.map(user,UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getNotFriends(String username) {
        List<User> notFriends = this.userRepository.getNotFriends(username);

        return notFriends.stream().map(f->this.modelMapper.map(f,UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        this.userRepository.update(user);

    }
}
