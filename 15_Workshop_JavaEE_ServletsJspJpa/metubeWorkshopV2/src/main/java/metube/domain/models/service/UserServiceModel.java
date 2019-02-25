package metube.domain.models.service;

import metube.domain.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {
    private String id;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private UserRole userRole;
    private List<TubeServiceModel> tubes;

    public UserServiceModel() {
        this.tubes=new ArrayList<>();
    }

    public UserServiceModel(String username, String password, String confirmPassword, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public List<TubeServiceModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<TubeServiceModel> tubes) {
        this.tubes = tubes;
    }
}
