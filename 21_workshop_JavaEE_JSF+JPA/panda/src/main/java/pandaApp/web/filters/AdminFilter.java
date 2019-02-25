package pandaApp.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/faces/view/packages/create-package.xhtml",
        "/faces/view/packages/delivered.xhtml",
        "/faces/view/packages/pending.xhtml",
        "/faces/view/packages/shipped.xhtml"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if(session.getAttribute("role")==null){
            String contextPath = req.getContextPath();
            String homeUrl=contextPath+"/faces/view/home.xhtml";
            resp.sendRedirect(homeUrl);
            return;
        }
        chain.doFilter(req,resp);
    }
}
