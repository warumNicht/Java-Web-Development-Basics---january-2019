package pandaApp.config;

import org.modelmapper.ModelMapper;
import pandaApp.repositories.UserRepository;
import pandaApp.repositories.UserRepositoryImpl;
import pandaApp.services.UserService;
import pandaApp.services.UserServiceImpl;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class AppConfig {

    @Produces
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Produces
    public EntityManager entityManager(){
        return Persistence.createEntityManagerFactory("panda")
                .createEntityManager();
    }

}
