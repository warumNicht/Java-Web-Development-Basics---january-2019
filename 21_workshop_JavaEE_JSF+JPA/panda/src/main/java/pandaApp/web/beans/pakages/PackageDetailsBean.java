package pandaApp.web.beans.pakages;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.enums.Status;
import pandaApp.domain.models.serviceModels.PackageServiceModel;
import pandaApp.domain.models.viewModels.PackageDetailsViewModel;
import pandaApp.domain.models.viewModels.PackageViewModel;
import pandaApp.services.PackageService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class PackageDetailsBean {
    private PackageService packageService;
    private ModelMapper modelMapper;

    private PackageDetailsViewModel model;

    public PackageDetailsBean() {

    }

    @Inject
    public PackageDetailsBean(PackageService packageService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.model=new PackageDetailsViewModel();
        this.packageService = packageService;
        this.initializeDetails();
    }


    public PackageDetailsViewModel getModel() {
        return model;
    }

    private void initializeDetails() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        String id = request.getParameter("id");
        PackageServiceModel packageServiceById = this.packageService.findById(id);
        PackageDetailsViewModel model = this.modelMapper.map(packageServiceById, PackageDetailsViewModel.class);

        if(packageServiceById.getStatus().equals(Status.Pending)){
            model.setEstimatedDeliveryDate("N/A");
        }else if(packageServiceById.getStatus().equals(Status.Shipped)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
            String formatDateTime = packageServiceById.getEstimatedDeliveryDate().format(formatter);
            model.setEstimatedDeliveryDate(formatDateTime);
        }else if(packageServiceById.getStatus().equals(Status.Delivered)){
            model.setEstimatedDeliveryDate("Delivered");
        }
        this.model=model;
    }
}
