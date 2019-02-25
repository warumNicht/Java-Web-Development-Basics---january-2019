package pandaApp.domain.models.serviceModels;

import pandaApp.domain.entities.enums.UserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {
    private String id;
    @NotEmpty
    @Size(min = 2,max = 20)
    private String username;

    @NotEmpty
    @Size(min = 2,max = 10)
    private String password;

    @Pattern(regexp = ".+@.+",message = "Email must contain @!")
    private String email;
    private UserRole role;

    private List<PackageServiceModel> packages;
    private List<ReceiptServiceModel> receipts;

    public UserServiceModel() {
        this.packages=new ArrayList<>();
        this.receipts=new ArrayList<>();
    }

    public UserServiceModel(String username, String password, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.packages=new ArrayList<>();
        this.receipts=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<PackageServiceModel> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageServiceModel> packages) {
        this.packages = packages;
    }

    public List<ReceiptServiceModel> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<ReceiptServiceModel> receipts) {
        this.receipts = receipts;
    }
}
