package application.repositories;

import application.domain.entities.Job;

public interface JobRepository extends GenericRepository<Job,String> {
    void deleteById(String id);
}
