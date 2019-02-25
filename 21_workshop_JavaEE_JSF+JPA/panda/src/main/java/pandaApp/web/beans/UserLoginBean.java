package pandaApp.web.beans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.enums.UserRole;
import pandaApp.domain.models.binding.UserLoginBinding;
import pandaApp.domain.models.serviceModels.UserServiceModel;
import pandaApp.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private UserLoginBinding userLoginBinding;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userLoginBinding=new UserLoginBinding();
    }

    public UserLoginBinding getUserLoginBinding() {
        return userLoginBinding;
    }

    public void login() throws IOException {
        ExternalContext externalContext =
                FacesContext.getCurrentInstance().getExternalContext();

        UserServiceModel userServiceModel = this.modelMapper.map(this.userLoginBinding, UserServiceModel.class);
        UserServiceModel loggedInUser = this.userService.userLogin(userServiceModel);

        if(loggedInUser==null){
            externalContext.redirect("/faces/view/login.xhtml");
            return;
        }
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.setAttribute("username",loggedInUser.getUsername());
        if(loggedInUser.getRole().equals(UserRole.Admin)){
            session.setAttribute("role","admin");
        }
        externalContext.redirect("/faces/view/home.xhtml");
    }
}
