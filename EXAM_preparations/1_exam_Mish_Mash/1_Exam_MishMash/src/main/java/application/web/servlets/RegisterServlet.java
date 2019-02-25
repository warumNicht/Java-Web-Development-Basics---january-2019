package application.web.servlets;

import application.domain.models.binding.UserRegisterBindingModel;
import application.domain.models.serviceModels.UserServiceModel;
import application.services.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Inject
    public RegisterServlet(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegisterBindingModel registerUser = (UserRegisterBindingModel) req.getAttribute("addChannel");
        UserServiceModel userServiceModel = this.modelMapper.map(registerUser, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);

        resp.sendRedirect("/login");
    }
}
