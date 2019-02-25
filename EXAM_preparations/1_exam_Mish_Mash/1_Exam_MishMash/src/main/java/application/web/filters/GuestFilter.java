package application.web.filters;

import application.domain.models.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/logout","/home","/channels/*"})
public class GuestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Object attribute = req.getSession().getAttribute("username");
        if(attribute==null){
            resp.sendRedirect("/");
            return;
        }
        chain.doFilter(request, response);
    }
}
