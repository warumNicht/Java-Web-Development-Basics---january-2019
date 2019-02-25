package application.web.beans;

import application.domain.models.bindingModels.UserLoginBindingModel;
import application.domain.models.serviceModels.UserServiceModel;
import application.domain.models.viewModels.UserHomeViewModel;
import application.services.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<UserHomeViewModel> getUsersNotFriends(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(true);
        String username = (String) session.getAttribute("username");
        UserServiceModel loggedUser = this.userService.findByUsername(username);

        List<UserServiceModel> notFriends = this.userService.getNotFriends(loggedUser.getUsername());

        return notFriends.stream()
                .map(f->this.modelMapper.map(f,UserHomeViewModel.class))
                .collect(Collectors.toList());
    }


}
