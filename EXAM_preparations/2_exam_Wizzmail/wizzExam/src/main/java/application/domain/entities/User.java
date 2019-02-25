package application.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(name = "username",nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "first_name",nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name",nullable = false, length = 30)
    private String lastName;

    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL)
    private List<Email> sentEmails;

    @ManyToMany(mappedBy = "recipients")
    private List<Email> receivedEmails;


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

    public List<Email> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(List<Email> sentEmails) {
        this.sentEmails = sentEmails;
    }

    public List<Email> getReceivedEmails() {
        return receivedEmails;
    }

    public void setReceivedEmails(List<Email> receivedEmails) {
        this.receivedEmails = receivedEmails;
    }
}
