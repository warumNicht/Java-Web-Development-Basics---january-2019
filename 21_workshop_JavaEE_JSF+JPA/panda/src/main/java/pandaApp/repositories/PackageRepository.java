package pandaApp.repositories;

import pandaApp.domain.entities.Package;
import pandaApp.domain.entities.enums.Status;

import java.util.List;

public interface PackageRepository extends GenericRepository<Package,String> {

    List<Package> findAllByStatus(Status status);

    void updatePackage(Package aPackage);
}
