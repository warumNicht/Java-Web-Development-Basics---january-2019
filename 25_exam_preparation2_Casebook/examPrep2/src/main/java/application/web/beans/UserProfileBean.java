package application.web.beans;

import application.domain.models.serviceModels.UserServiceModel;
import application.domain.models.viewModels.UserProfileViewModel;
import application.services.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class UserProfileBean {
    private UserService userService;
    private ModelMapper modelMapper;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserProfileViewModel getUserProfile(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String friendId = request.getParameter("id");

        if(friendId!=null){
            UserServiceModel byUsername = this.userService.findById(friendId);
            UserProfileViewModel profileViewModel = this.modelMapper.map(byUsername, UserProfileViewModel.class);
            return profileViewModel;
        }

        HttpSession session = (HttpSession) externalContext.getSession(true);
        String username = (String) session.getAttribute("username");
        UserServiceModel byUsername = this.userService.findByUsername(username);
        UserProfileViewModel profileViewModel = this.modelMapper.map(byUsername, UserProfileViewModel.class);
        return profileViewModel;
    }


}
