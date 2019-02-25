package application.web.beans;

import application.domain.models.bindingModels.UserLoginBindingModel;
import application.domain.models.bindingModels.UserRegisterBindingModel;
import application.domain.models.serviceModels.UserServiceModel;
import application.services.UserService;
import org.modelmapper.ModelMapper;

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

    private UserLoginBindingModel userLoginBindingModel;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userLoginBindingModel=new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        UserServiceModel userServiceModel = this.modelMapper.map(this.userLoginBindingModel, UserServiceModel.class);

        UserServiceModel serviceModel = this.userService.userLogin(userServiceModel);
        if(serviceModel==null){
            externalContext.redirect("/faces/view/login.xhtml");
            return;
        }
        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.setAttribute("username",serviceModel.getUsername());

        externalContext.redirect("/faces/view/home.xhtml");
    }
}
