package metube.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;

        if(req.getSession().getAttribute("username")==null){
            resp.sendRedirect("/login");
            return;
        }
        if(req.getSession().getAttribute("admin")==null){
            resp.sendRedirect("/home");
            return;
        }
        chain.doFilter(req,resp);
    }
}
