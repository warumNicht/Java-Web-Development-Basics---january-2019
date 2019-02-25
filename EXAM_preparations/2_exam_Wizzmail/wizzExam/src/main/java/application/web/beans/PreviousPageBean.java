package application.web.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Named
@RequestScoped
public class PreviousPageBean {

    public PreviousPageBean() {
    }

    public void goBack() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        Map<String, String> parameterMap = externalContext.getRequestParameterMap();
        String referer = parameterMap.get("precedent");

        externalContext.redirect(referer);
    }
}
