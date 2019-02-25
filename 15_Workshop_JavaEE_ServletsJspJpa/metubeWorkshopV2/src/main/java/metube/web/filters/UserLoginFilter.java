package metube.web.filters;

import metube.domain.models.binding.UserLoginBindingModel;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/login")
public class UserLoginFilter implements Filter {
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

            UserLoginBindingModel loginBindingModel = new UserLoginBindingModel(username, password);

            req.setAttribute("loginModel",loginBindingModel);
        }
        chain.doFilter(req,resp);
    }
}
