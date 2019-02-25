package application.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter(value = {"/register/*", "/*"}, filterName = "char")
public class CharSetFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String requestEncoding = filterConfig.getInitParameter("requestEncoding");
        if(requestEncoding==null){
            this.encoding="UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(this.encoding);
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request,response);
    }
}
