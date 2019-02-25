package pandaApp.services;

import pandaApp.domain.entities.enums.Status;
import pandaApp.domain.models.serviceModels.PackageServiceModel;

import java.util.List;
import java.util.Optional;

public interface PackageService  {

    void packageCreate(PackageServiceModel packageServiceModel);

    List<PackageServiceModel> findAllByStatus(Status status);

    PackageServiceModel findById(String id);

    void update(PackageServiceModel packageServiceModel);
}
