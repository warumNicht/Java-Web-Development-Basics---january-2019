package metube.web.servlets;

import metube.domain.enums.UserRole;
import metube.domain.models.binding.UserLoginBindingModel;
import metube.domain.models.service.UserServiceModel;
import metube.services.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    private final UserService userService;
    private final ModelMapper mapper;

    @Inject
    public UserLoginServlet(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserLoginBindingModel loginModel = (UserLoginBindingModel) req.getAttribute("loginModel");
        UserServiceModel userServiceModel = this.mapper.map(loginModel, UserServiceModel.class);
        if(!this.userService.loginUser(userServiceModel)){
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("username",loginModel.getUsername());
        userServiceModel=this.userService.findUserByName(loginModel.getUsername());
        if(userServiceModel.getUserRole().equals(UserRole.Admin)){
            req.getSession().setAttribute("admin","adminTrue");
        }
        resp.sendRedirect("/home");
    }
}
