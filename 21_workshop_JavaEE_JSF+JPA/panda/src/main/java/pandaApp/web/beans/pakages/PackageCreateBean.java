package pandaApp.web.beans.pakages;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.binding.PackageCreateBindingModel;
import pandaApp.domain.models.serviceModels.PackageServiceModel;
import pandaApp.domain.models.serviceModels.UserServiceModel;
import pandaApp.services.PackageService;
import pandaApp.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class PackageCreateBean {
    private PackageCreateBindingModel packageCreateBindingModel;

    private UserService userService;
    private PackageService packageService;
    private ModelMapper modelMapper;

    public PackageCreateBean() {
    }

    @Inject
    public PackageCreateBean(UserService userService, PackageService packageService, ModelMapper modelMapper) {
        this.userService = userService;
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.packageCreateBindingModel=new PackageCreateBindingModel();
    }

    public PackageCreateBindingModel getPackageCreateBindingModel() {
        return packageCreateBindingModel;
    }

    public void setPackageCreateBindingModel(PackageCreateBindingModel packageCreateBindingModel) {
        this.packageCreateBindingModel = packageCreateBindingModel;
    }

    public List<String> findAllUsers(){
        return this.userService.findAll().stream()
                .map(UserServiceModel::getUsername)
                .collect(Collectors.toList());
    }

    public void create() throws IOException {
        PackageServiceModel packageServiceModel =
                this.modelMapper.map(this.packageCreateBindingModel, PackageServiceModel.class);
        UserServiceModel userServiceModel = this.userService.
                findByUsername(this.packageCreateBindingModel.getRecipient())
                .orElse(null);

        packageServiceModel.setRecipient(userServiceModel);

        this.packageService.packageCreate(packageServiceModel);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("/faces/view/home.xhtml");
    }
}
