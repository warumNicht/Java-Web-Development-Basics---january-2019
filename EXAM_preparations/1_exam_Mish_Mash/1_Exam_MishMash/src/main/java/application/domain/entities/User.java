package application.domain.entities;

import application.domain.entities.enums.UserRole;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "users_channels",
    joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "channel_id",referencedColumnName = "id"))
    private Set<Channel> followedChannels;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role",nullable = false)
    private UserRole role;

    public User() {
        this.followedChannels=new LinkedHashSet<>();
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

    public Set<Channel> getFollowedChannels() {
        return followedChannels;
    }

    public void setFollowedChannels(Set<Channel> followedChannels) {
        this.followedChannels = followedChannels;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
