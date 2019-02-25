package application.domain.models.viewModels;

import application.domain.entities.enums.Gender;

public class UserProfileViewModel {
    private String username;
    private Gender gender;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
