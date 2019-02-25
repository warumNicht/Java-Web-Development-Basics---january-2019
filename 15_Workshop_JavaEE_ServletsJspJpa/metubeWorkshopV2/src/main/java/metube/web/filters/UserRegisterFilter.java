package metube.web.filters;

import metube.domain.models.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/register")
public class UserRegisterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;

        if(req.getMethod().toLowerCase().equals("post")){
            if (request.getCharacterEncoding() == null) {
                request.setCharacterEncoding("UTF-8");
            }

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");
            String email = req.getParameter("email");

            UserRegisterBindingModel userRegisterBindingModel=
                    new UserRegisterBindingModel(username,password,confirmPassword,email);

            req.setAttribute("bindingModel",userRegisterBindingModel);
        }
        chain.doFilter(req,resp);
    }
}
