package application.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter({"/","/login","/register"})
public class LoggedUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        if(req.getSession().getAttribute("username")!=null){
            HttpServletResponse resp= (HttpServletResponse) response;
            resp.sendRedirect("/home");
            return;
        }
        chain.doFilter(request,response);
    }
}
