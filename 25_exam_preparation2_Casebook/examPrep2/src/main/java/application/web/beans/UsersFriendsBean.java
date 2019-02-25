package application.web.beans;

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
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UsersFriendsBean {
    private UserService userService;
    private ModelMapper modelMapper;

    public UsersFriendsBean() {
    }

    @Inject
    public UsersFriendsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<UserHomeViewModel> getUsersFriends(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(true);
        String username = (String) session.getAttribute("username");
        UserServiceModel byUsername = this.userService.findByUsername(username);

        return byUsername.getFriends().stream()
                .map(f->this.modelMapper.map(f,UserHomeViewModel.class))
                .collect(Collectors.toList());
    }


}
