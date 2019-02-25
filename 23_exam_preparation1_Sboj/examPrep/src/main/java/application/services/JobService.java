package application.services;

import application.domain.models.serviceModels.JobServiceModel;

import java.util.List;

public interface JobService {
    void jobRegister(JobServiceModel jobServiceModel);

    List<JobServiceModel> findAll();

    JobServiceModel findById(String id);

    void delete(String id);
}
