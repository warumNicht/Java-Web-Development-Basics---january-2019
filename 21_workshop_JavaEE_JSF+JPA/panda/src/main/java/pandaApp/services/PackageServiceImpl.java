package pandaApp.services;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.Package;
import pandaApp.domain.entities.enums.Status;
import pandaApp.domain.models.serviceModels.PackageServiceModel;
import pandaApp.domain.models.serviceModels.UserServiceModel;
import pandaApp.repositories.PackageRepository;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;
    private final ModelMapper modelMapper;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository, ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void packageCreate(PackageServiceModel packageServiceModel) {
        Package aPackage = this.modelMapper.map(packageServiceModel, Package.class);

        aPackage.setStatus(Status.Pending);
        this.packageRepository.save(aPackage);
    }

    @Override
    public List<PackageServiceModel> findAllByStatus(Status status) {
        return this.packageRepository.findAllByStatus(status)
                .stream()
                .map(p->this.modelMapper.map(p,PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PackageServiceModel findById(String id) {
        Package aPackage = this.packageRepository.findById(id).orElse(null);
        return this.modelMapper.map(aPackage,PackageServiceModel.class);
    }

    @Override
    public void update(PackageServiceModel packageServiceModel) {
        Package aPackage = this.modelMapper.map(packageServiceModel, Package.class);
        if(aPackage.getStatus().equals(Status.Shipped)){
            this.changeDeliveryDate(aPackage);
        }
        this.packageRepository.updatePackage(aPackage);
    }

    private void changeDeliveryDate(Package aPackage){
        Random random=new Random();
        int nextInt = random.nextInt(20);
        nextInt+=20;
        aPackage.setEstimatedDeliveryDate(LocalDateTime.now().plusDays(nextInt));
    }
}
