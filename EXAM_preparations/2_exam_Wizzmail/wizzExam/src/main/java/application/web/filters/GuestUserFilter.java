package application.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({ "/faces/view/packages/*","/faces/view/receipts/*"})
public class GuestUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if(session.getAttribute("username")==null){
            String contextPath = req.getContextPath();
            String homeUrl=contextPath+"/faces/view/login.xhtml";
            resp.sendRedirect(homeUrl);
            return;
        }
        chain.doFilter(req,resp);
    }
}