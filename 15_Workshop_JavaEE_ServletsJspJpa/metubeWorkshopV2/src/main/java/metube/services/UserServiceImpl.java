package metube.services;

import metube.domain.entities.User;
import metube.domain.enums.UserRole;
import metube.domain.models.service.UserServiceModel;
import metube.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import javax.inject.Inject;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User user = this.mapper.map(userServiceModel, User.class);
        String sha256hexPassword = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(sha256hexPassword);
        if(this.userRepository.size()==0){
            user.setUserRole(UserRole.Admin);
        }else {
            user.setUserRole(UserRole.User);
        }

        try {
            this.userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean loginUser(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByNameAndPassword(userServiceModel.getUsername(),
                DigestUtils.sha256Hex(userServiceModel.getPassword())).orElse(null);
        if(user!=null){
            return true;
        }
        return false;
    }

    @Override
    public UserServiceModel findUserByName(String username) {
        User user = this.userRepository.findByName(username).orElse(null);
        if(user==null){
            throw new IllegalArgumentException();
        }
        UserServiceModel userServiceModel = this.mapper.map(user, UserServiceModel.class);
        return userServiceModel;
    }
}
