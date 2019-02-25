package pandaApp.web.beans.pakages;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.enums.Status;
import pandaApp.domain.models.serviceModels.PackageServiceModel;
import pandaApp.domain.models.viewModels.PackageViewModel;
import pandaApp.services.PackageService;

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
public class PendingPackagesBean {
    private PackageService packageService;
    private ModelMapper modelMapper;
    private int index;

    public PendingPackagesBean() {
    }

    @Inject
    public PendingPackagesBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    public List<PackageViewModel> findPending(){
        List<PackageServiceModel> allByStatus = this.packageService.findAllByStatus(Status.Pending);
        List<PackageViewModel> packageViewModels = allByStatus.stream()
                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());

        return packageViewModels;
    }

    public int getIndex() {
        this.index++;
        return index;
    }

    public void incrementStatus(String id) throws IOException {
        PackageServiceModel packageServiceById = this.packageService.findById(id);
        int ordinal = packageServiceById.getStatus().ordinal();
        packageServiceById.setStatus(Status.values()[++ordinal]);
        this.packageService.update(packageServiceById);
        ExternalContext externalContext =
                FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/faces/view/packages/pending.xhtml");
    }
}
