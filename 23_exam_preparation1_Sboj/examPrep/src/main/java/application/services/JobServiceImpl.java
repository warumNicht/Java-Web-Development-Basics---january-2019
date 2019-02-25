package application.services;

import application.domain.entities.Job;
import application.domain.models.serviceModels.JobServiceModel;
import application.repositories.JobRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void jobRegister(JobServiceModel jobServiceModel) {
        Job job = this.modelMapper.map(jobServiceModel, Job.class);
        this.jobRepository.save(job);
    }

    @Override
    public List<JobServiceModel> findAll() {
        return this.jobRepository.findAll().stream()
                .map(j->this.modelMapper.map(j,JobServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobServiceModel findById(String id) {
        Job job = this.jobRepository.findById(id).orElse(null);

        return this.modelMapper.map(job,JobServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.jobRepository.deleteById(id);
    }
}
