package application.web.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLogoutBean {

    public UserLogoutBean() {
    }


    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(true);
        session.invalidate();

        externalContext.redirect("/faces/view/index.xhtml");
    }
}
