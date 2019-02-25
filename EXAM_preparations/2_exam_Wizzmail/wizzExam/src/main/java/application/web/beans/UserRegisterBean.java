package application.web.beans;

import application.domain.models.bindingModels.UserRegisterBindingModel;
import application.domain.models.serviceModels.UserServiceModel;
import application.services.UserService;
import application.util.ValidationUtil;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserService userService;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    private UserRegisterBindingModel userRegisterBindingModel;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
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
        HttpSession session = (HttpSession) externalContext.getSession(true);
        UserServiceModel serviceModel = this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class);

//        if(!this.validationUtil.isValid(serviceModel)){
//
//            List<String> violations = this.validationUtil.getViolations(serviceModel)
//                    .stream()
//                    .map(e -> e.getMessage())
//                    .collect(Collectors.toList());
//            session.setAttribute("er",violations);
//
//            if(!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())){
//                session.setAttribute("notMatching","Not matching passwords!");
//            }
//
//            externalContext.redirect("/faces/view/register.xhtml");
//            return;
//        }else  if(!this.userRegisterBindingModel.getPassword()
//                .equals(this.userRegisterBindingModel.getConfirmPassword())){
//            session.setAttribute("notMatching","Not matching passwords!");
//
//            externalContext.redirect("/faces/view/register.xhtml");
//            return;
//        }

//        session.setAttribute("notMatching",null);
//        session.setAttribute("er",null);

        UserServiceModel userServiceModel = this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class);
        this.userService.userRegister(userServiceModel);
        externalContext.redirect("/faces/view/login.xhtml");
    }
}
