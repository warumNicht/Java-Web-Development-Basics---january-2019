package application.web.beans;

import application.domain.models.serviceModels.UserServiceModel;
import application.services.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserUnfriendBean {
    private UserService userService;
    private ModelMapper modelMapper;

    public UserUnfriendBean() {
    }

    @Inject
    public UserUnfriendBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public void unFriend (String username) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        HttpSession session = (HttpSession) externalContext.getSession(true);
        String loggedUsername = (String) session.getAttribute("username");
        UserServiceModel user = this.userService.findByUsername(loggedUsername);
        UserServiceModel friend = this.userService.findByUsername(username);

        List<UserServiceModel> afterRemove = user.getFriends().stream()
                .filter(f -> !f.getUsername().equals(friend.getUsername()))
                .collect(Collectors.toList());

        user.setFriends(afterRemove);

        this.userService.update(user);

        List<UserServiceModel> friendAfterRemove = friend.getFriends().stream()
                .filter(f -> !f.getUsername().equals(user.getUsername()))
                .collect(Collectors.toList());
        friend.setFriends(friendAfterRemove);

        this.userService.update(friend);

        externalContext.redirect("/faces/view/friends.xhtml");
    }
}
