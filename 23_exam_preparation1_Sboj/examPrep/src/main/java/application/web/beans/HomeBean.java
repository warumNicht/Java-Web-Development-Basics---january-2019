package application.web.beans;

import application.domain.models.viewModels.JobViewModel;
import application.services.JobService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private JobService jobService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    public List<JobViewModel> findAllJobs() {
        return this.jobService.findAll().stream()
                .map(j -> {
                    JobViewModel viewModel = this.modelMapper.map(j, JobViewModel.class);
                    viewModel.setSector(j.getSector().name().toLowerCase());
                    return viewModel;
                })
                .collect(Collectors.toList());
    }


}
