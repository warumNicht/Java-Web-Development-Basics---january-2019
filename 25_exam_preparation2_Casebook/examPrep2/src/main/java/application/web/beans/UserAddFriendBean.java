package application.web.beans;

import application.domain.models.serviceModels.UserServiceModel;
import application.services.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserAddFriendBean {
    private UserService userService;
    private ModelMapper modelMapper;

    public UserAddFriendBean() {
    }

    @Inject
    public UserAddFriendBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public void addFriend(String friendId) throws IOException {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

//        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//        String friendId = request.getParameter("id");

        HttpSession session = (HttpSession) externalContext.getSession(true);
        String username = (String) session.getAttribute("username");
        UserServiceModel user = this.userService.findByUsername(username);

        UserServiceModel friend = this.userService.findById(friendId);
        friend.getFriends().add(user);
        user.getFriends().add(friend);

        this.userService.update(user);
        this.userService.update(friend);



        externalContext.redirect("/faces/view/home.xhtml");
    }
}
