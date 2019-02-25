package pandaApp.web.beans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.binding.UserRegisterBindingModel;
import pandaApp.domain.models.serviceModels.UserServiceModel;
import pandaApp.services.UserService;
import pandaApp.util.ValidationUtil;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserRegisterBindingModel userRegisterBindingModel;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    private UserService userService;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        if (!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())) {
            throw new IllegalArgumentException("Not matching passwords!");
        }
        ExternalContext externalContext =
                FacesContext.getCurrentInstance().getExternalContext();

        UserServiceModel userServiceModel = this.modelMapper
                .map(this.userRegisterBindingModel, UserServiceModel.class);

        if (!this.validationUtil.isValid(userServiceModel)) {
            List<ConstraintViolation<UserServiceModel>> violations = this.validationUtil
                    .getViolations(userServiceModel)
                    .stream()
                    .collect(Collectors.toList());
            HttpSession session = (HttpSession) externalContext.getSession(false);
            session.setAttribute("err", violations);

            externalContext.redirect("/faces/view/errors.xhtml");
            return;
        }
        this.userService.userRegister(userServiceModel);
        externalContext.redirect("/faces/view/login.xhtml");
    }
}
