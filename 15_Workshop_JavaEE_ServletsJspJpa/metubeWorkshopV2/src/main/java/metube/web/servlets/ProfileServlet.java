package metube.web.servlets;

import metube.domain.models.service.UserServiceModel;
import metube.domain.models.view.UserProfileViewModel;
import metube.services.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final UserService userService;
    private final ModelMapper mapper;

    @Inject
    public ProfileServlet(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String)req.getSession().getAttribute("username");
        UserServiceModel userByName = this.userService.findUserByName(username);
        UserProfileViewModel userProfileViewModel = this.mapper.map(userByName, UserProfileViewModel.class);

        req.setAttribute("userProfileViewModel",userProfileViewModel);

        req.getRequestDispatcher("/jsp/profile.jsp").forward(req,resp);
    }
}
