package application.web.servlets;

import application.domain.models.serviceModels.UserServiceModel;
import application.services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;

    @Inject
    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if(this.userService.noUsers()){
//            resp.sendRedirect("/register");
//            return;
//        }
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserServiceModel userServiceModel = this.userService.checkExistentUser(username, password);
        if(userServiceModel!=null){
            req.getSession().setAttribute("username",username);
            if(userServiceModel.getRole().equals("Admin")){
                req.getSession().setAttribute("admin","admin");
            }
            resp.sendRedirect("/home");
            return;
        }
        resp.sendRedirect("/login");
    }
}
