package metube.domain.models.view;

import java.util.List;

public class UserHomeViewModel {
    private String username;
    private List<UserHomeTubesViewModel> tubes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserHomeTubesViewModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<UserHomeTubesViewModel> tubes) {
        this.tubes = tubes;
    }
}
