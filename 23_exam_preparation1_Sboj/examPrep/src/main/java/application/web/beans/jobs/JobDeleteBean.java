package application.web.beans.jobs;

import application.domain.models.serviceModels.JobServiceModel;
import application.domain.models.viewModels.JobViewDetailsModel;
import application.services.JobService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Named
@RequestScoped
public class JobDeleteBean {
    private JobService jobService;
    private ModelMapper modelMapper;

    private JobViewDetailsModel model;

    private String id;

    public JobDeleteBean() {
    }

    @Inject
    public JobDeleteBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
        this.initializeDeletingModel();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void initializeDeletingModel() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String id = request.getParameter("id");
//        if(id!=null){
//            session.setAttribute("del",id);
//        }else {
//            id=(String) session.getAttribute("del");
//        }
        if(id!=null){
            JobServiceModel model=this.jobService.findById(id);
            this.model=this.modelMapper.map(model,JobViewDetailsModel.class);
        }

    }

    public JobViewDetailsModel getModel() {
        return model;
    }

    public void setModel(JobViewDetailsModel model) {
        this.model = model;
    }

    public void delete() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String,String> params =
                externalContext.getRequestParameterMap();
        String id = params.get("idToDel");

        this.jobService.delete(id);

        externalContext.redirect("/faces/view/home.xhtml");
    }
}
