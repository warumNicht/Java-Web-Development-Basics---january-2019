package application.web.beans;

import application.domain.models.bindingModels.UserRegisterBindingModel;
import application.domain.models.serviceModels.UserServiceModel;
import application.services.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserService userService;
    private ModelMapper modelMapper;

    private UserRegisterBindingModel userRegisterBindingModel;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        if(!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())){
            throw new IllegalArgumentException("Not matching passwords!");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class);
        this.userService.userRegister(userServiceModel);
        externalContext.redirect("/faces/view/login.xhtml");
    }
}
