package application.web.beans.jobs;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Named
@RequestScoped
public class DelBean  {

    public DelBean() {

    }


    public  void delete(String id) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String,String> params =
                externalContext.getRequestParameterMap();
        String action = params.get("idToDel");

        HttpServletRequest req= (HttpServletRequest) externalContext.getRequest();
        String del = (String) req.getAttribute("del");

        externalContext.redirect("/faces/view/home.xhtml");
    }
}
