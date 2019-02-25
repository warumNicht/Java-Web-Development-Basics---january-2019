package metube.domain.entities;

import metube.domain.enums.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole userRole;

    @OneToMany(targetEntity = Tube.class,mappedBy = "uploader",fetch = FetchType.EAGER)
    private List<Tube> tubes;

    public User() {
        this.tubes=new ArrayList<>();
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Tube> getTubes() {
        return tubes;
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }
}
