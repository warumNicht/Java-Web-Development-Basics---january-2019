package metube.web.filters;

import metube.domain.models.binding.TubeCreateBindingModel;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().toLowerCase().equals("post")) {
            String name = new String(req.getParameter("name").getBytes("iso-8859-1"), "utf-8");
            String description = new String(req.getParameter("description").getBytes("iso-8859-1"), "utf-8");
            String youTubeLink = new String(req.getParameter("youTubeLink").getBytes("iso-8859-1"), "utf-8");
            String uploader = new String(req.getParameter("uploader").getBytes("iso-8859-1"), "utf-8");

            TubeCreateBindingModel tubeCreateBindingModel =
                    new TubeCreateBindingModel(name, description, youTubeLink, uploader);

            req.setAttribute("tubeCreateBindingModel", tubeCreateBindingModel);
        }
        chain.doFilter(req, resp);
    }
}
