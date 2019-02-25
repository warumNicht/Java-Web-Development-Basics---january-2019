package metube.web.filters;

import metube.domain.models.binding.TubeUploadBindingModel;
import metube.domain.models.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/tube/upload")
public class TubeUploadFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;

        if(req.getMethod().toLowerCase().equals("post")){
            if (request.getCharacterEncoding() == null) {
                request.setCharacterEncoding("UTF-8");
            }

            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String youtubeLink = req.getParameter("youtubeLink");
            String description = req.getParameter("description");
            String uploader = (String)req.getSession().getAttribute("username");

            TubeUploadBindingModel bindingModel=
                    new TubeUploadBindingModel(title,author,youtubeLink,
                            description,uploader);

          req.setAttribute("uploadTubeModel",bindingModel);
        }
        chain.doFilter(req,resp);
    }
}
