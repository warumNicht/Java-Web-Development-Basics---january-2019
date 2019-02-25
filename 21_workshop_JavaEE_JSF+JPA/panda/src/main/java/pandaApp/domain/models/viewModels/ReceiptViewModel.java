package pandaApp.domain.models.viewModels;

import pandaApp.domain.models.serviceModels.PackageServiceModel;
import pandaApp.domain.models.serviceModels.UserServiceModel;

import java.math.BigDecimal;

public class ReceiptViewModel {
    private String id;
    private BigDecimal fee;
    private String issuedOn;
    private UserServiceModel recipient;
    private PackageServiceModel aPackage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(String issuedOn) {
        this.issuedOn = issuedOn;
    }

    public UserServiceModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserServiceModel recipient) {
        this.recipient = recipient;
    }

    public PackageServiceModel getaPackage() {
        return aPackage;
    }

    public void setaPackage(PackageServiceModel aPackage) {
        this.aPackage = aPackage;
    }
}
