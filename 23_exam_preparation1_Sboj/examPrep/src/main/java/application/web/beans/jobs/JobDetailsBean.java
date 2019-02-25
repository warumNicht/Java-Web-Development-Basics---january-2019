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

@Named
@RequestScoped
public class JobDetailsBean {
    private JobService jobService;
    private ModelMapper modelMapper;

    private JobViewDetailsModel model;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
        this.initializeModel();
    }

    private void initializeModel() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String id = request.getParameter("id");
        JobServiceModel model=this.jobService.findById(id);
        this.model=this.modelMapper.map(model,JobViewDetailsModel.class);
    }

    public JobViewDetailsModel getModel() {
        return model;
    }

    public void setModel(JobViewDetailsModel model) {
        this.model = model;
    }
}
