package pandaApp.domain.entities;

import pandaApp.domain.entities.enums.UserRole;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username",nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role",nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "recipient",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Package> packages;

    @OneToMany(mappedBy = "recipient",cascade = CascadeType.ALL)
    private List<Receipt> receipts;


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

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}
