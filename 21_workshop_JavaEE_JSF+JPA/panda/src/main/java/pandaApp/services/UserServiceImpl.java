package pandaApp.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.User;
import pandaApp.domain.entities.enums.UserRole;
import pandaApp.domain.models.serviceModels.UserServiceModel;
import pandaApp.repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
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
        this.setUserRole(user);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel userLogin(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername()).orElse(null);
        String hashedPassword = DigestUtils.sha256Hex(userServiceModel.getPassword());

        if(user==null||!hashedPassword.equals(user.getPassword())){
            return null;
        }
        return this.modelMapper.map(user,UserServiceModel.class);
    }

    @Override
    public Optional<UserServiceModel> findByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        if(user==null){
            return Optional.empty();
        }
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        return Optional.of(userServiceModel);
    }

    @Override
    public List<UserServiceModel> findAll() {
        return this.userRepository.findAll().stream()
                .map(u->this.modelMapper.map(u,UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        this.userRepository.update(user);
    }


    private void setUserRole(User user){
        if(this.userRepository.size()==0){
            user.setRole(UserRole.Admin);
        }else {
            user.setRole(UserRole.User);
        }
    }
}
