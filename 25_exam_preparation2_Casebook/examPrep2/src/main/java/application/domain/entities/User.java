package application.domain.entities;

import application.domain.entities.enums.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(name = "username",nullable = false, unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender",nullable = false)
    private Gender gender;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "users_friends",
    joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id",referencedColumnName = "id"))
    private List<User> friends;

    public User() {
        this.friends=new ArrayList<>();
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
