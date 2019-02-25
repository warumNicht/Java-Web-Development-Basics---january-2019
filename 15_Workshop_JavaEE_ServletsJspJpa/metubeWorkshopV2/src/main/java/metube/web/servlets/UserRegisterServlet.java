package metube.web.servlets;

import metube.domain.models.binding.UserRegisterBindingModel;
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

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {
    private final UserService userService;
    private final ModelMapper mapper;

    @Inject
    public UserRegisterServlet(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegisterBindingModel bindingModel = (UserRegisterBindingModel) req.getAttribute("bindingModel");
        if(!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())){
            req.getRequestDispatcher("/jsp/register.jsp").forward(req,resp);
            return;
        }
        UserServiceModel userServiceModel = this.mapper.map(bindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);

        resp.sendRedirect("/login");
    }
}
