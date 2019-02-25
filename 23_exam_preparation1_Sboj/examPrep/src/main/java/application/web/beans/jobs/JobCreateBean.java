package application.web.beans.jobs;

import application.domain.models.bindingModels.JobCreateBindingModel;
import application.domain.models.serviceModels.JobServiceModel;
import application.services.JobService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobCreateBean {
    private JobService jobService;
    private ModelMapper modelMapper;

    private JobCreateBindingModel jobCreateBindingModel;

    public JobCreateBean() {
    }

    @Inject
    public JobCreateBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
        this.jobCreateBindingModel=new JobCreateBindingModel();
    }

    public JobCreateBindingModel getJobCreateBindingModel() {
        return jobCreateBindingModel;
    }

    public void setJobCreateBindingModel(JobCreateBindingModel jobCreateBindingModel) {
        this.jobCreateBindingModel = jobCreateBindingModel;
    }

    public void registerJob() throws IOException {
        JobServiceModel jobServiceModel = this.modelMapper.map(this.jobCreateBindingModel, JobServiceModel.class);
        this.jobService.jobRegister(jobServiceModel);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/faces/view/home.xhtml");
    }
}
