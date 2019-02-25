package pandaApp.web.beans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.Package;
import pandaApp.domain.entities.Receipt;
import pandaApp.domain.entities.User;
import pandaApp.domain.entities.enums.Status;
import pandaApp.domain.models.serviceModels.PackageServiceModel;
import pandaApp.domain.models.serviceModels.ReceiptServiceModel;
import pandaApp.domain.models.serviceModels.UserServiceModel;
import pandaApp.domain.models.viewModels.PackageViewModel;
import pandaApp.services.PackageService;
import pandaApp.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private UserService userService;
    private PackageService packageService;
    private ModelMapper modelMapper;

    List<PackageViewModel> usersPackages;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, PackageService packageService, ModelMapper modelMapper) {
        this.userService = userService;
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.initializePackages();
    }

    private void initializePackages() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String username = (String) session.getAttribute("username");
        UserServiceModel userServiceModel = this.userService.findByUsername(username).orElse(null);

        this.usersPackages=userServiceModel.getPackages().stream()
                .map(p->this.modelMapper.map(p,PackageViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> findPendingPackages(){
        List<PackageViewModel> pending = this.usersPackages.stream()
                .filter(p -> p.getStatus().equals(Status.Pending))
                .collect(Collectors.toList());
        return pending;
    }

    public List<PackageViewModel> findShippedPackages(){
        return this.usersPackages.stream()
                .filter(p -> p.getStatus().equals(Status.Shipped))
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> findDeliveredPackages(){
        return this.usersPackages.stream()
                .filter(p -> p.getStatus().equals(Status.Delivered))
                .collect(Collectors.toList());
    }

    public void acquire(String packageId) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String username = (String) session.getAttribute("username");

        UserServiceModel recipient = this.userService.findByUsername(username).orElse(null);
        PackageServiceModel packageService = this.packageService.findById(packageId);

        recipient.getPackages().stream()
                .filter(p->p.getId().equals(packageService.getId()))
                .forEach(p->{
                    p.setStatus(Status.Acquired);
                });

//        User recipient = this.modelMapper.map(userServiceModel, User.class);
//        Package aPackage = this.modelMapper.map(packageService, Package.class);

//        Receipt receipt=new Receipt();
//
//        receipt.setaPackage(aPackage);
//        receipt.setRecipient(recipient);
//

//
//        receipt.setFee(new BigDecimal(fee));
//        receipt.setIssuedOn(LocalDateTime.now());

        ReceiptServiceModel receipt=new ReceiptServiceModel();

        receipt.setaPackage(packageService);
        receipt.setRecipient(recipient);
        Double fee=packageService.getWeight()*2.67;

        receipt.setFee(new BigDecimal(fee));
        receipt.setIssuedOn(LocalDateTime.now());
        recipient.getReceipts().add(receipt);

        this.userService.update(recipient);

        externalContext.redirect("/faces/view/home.xhtml");

    }
}
