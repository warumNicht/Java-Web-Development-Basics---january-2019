package application.web.beans;

import application.domain.models.serviceModels.UserServiceModel;
import application.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class CurrentLoggedUserNamesBean {
    private UserService userService;

    public CurrentLoggedUserNamesBean() {
    }

    @Inject
    public CurrentLoggedUserNamesBean(UserService userService) {
        this.userService = userService;
    }

    public String getNames(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(true);
        String username = (String) session.getAttribute("username");
        if(username!=null){
            UserServiceModel logged = this.userService.findByUsername(username);
            return logged.getFirstName()+" "+logged.getLastName();
        }

        return "";
    }
}
