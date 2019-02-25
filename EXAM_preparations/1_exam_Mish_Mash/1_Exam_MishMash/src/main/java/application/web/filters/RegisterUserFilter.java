package application.web.filters;

import application.domain.models.binding.UserRegisterBindingModel;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class RegisterUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getMethod().toLowerCase().equals("post")) {
            if (request.getCharacterEncoding() == null) {
                request.setCharacterEncoding("UTF-8");
            }
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");
            String email = req.getParameter("email");

            if (!password.equals(confirmPassword)) {
                resp.sendRedirect("/register");
                return;
            }
            UserRegisterBindingModel userRegisterBindingModel= new UserRegisterBindingModel(username,password,email);
            req.setAttribute("addChannel",userRegisterBindingModel);
        }
        chain.doFilter(request, response);
    }
}
