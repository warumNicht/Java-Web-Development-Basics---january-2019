package application.domain.models.serviceModels;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserServiceModel {
    private String id;
    @Size(min = 3,max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9.].+$", message = "Username must contain digits, letters or .")
    private String username;

    @Size(min = 8, message = "Password must be min 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$",
            message = "Password must contain a-z,  A-Z and 0-9")
    private String password;

    @Size(min = 5,max = 30, message = "First name must be between 5 and 30 characters")
    private String firstName;

    @Size(min = 5,max = 30, message = "Last name must be between 5 and 30 characters")
    private String lastName;

    private List<EmailServiceModel> sentEmails;
    private List<EmailServiceModel> receivedEmails;


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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmailServiceModel> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(List<EmailServiceModel> sentEmails) {
        this.sentEmails = sentEmails;
    }

    public List<EmailServiceModel> getReceivedEmails() {
        return receivedEmails;
    }

    public void setReceivedEmails(List<EmailServiceModel> receivedEmails) {
        this.receivedEmails = receivedEmails;
    }
}
