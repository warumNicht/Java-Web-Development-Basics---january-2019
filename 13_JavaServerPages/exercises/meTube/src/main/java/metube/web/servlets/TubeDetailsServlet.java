package metube.web.servlets;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeDetailsViewModel;
import metube.services.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/tubes/details")
public class TubeDetailsServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        TubeServiceModel tubeByName = this.tubeService.findTubeByName(name);
        if(tubeByName==null){
            req.getRequestDispatcher("/jsp/notExistentTube.jsp").forward(req,resp);
            return;
        }
        TubeDetailsViewModel detailsViewModel = this.modelMapper.map(tubeByName, TubeDetailsViewModel.class);
        req.setAttribute("tubeDetailsViewModel",detailsViewModel);

        req.getRequestDispatcher("/jsp/details-tube.jsp").forward(req,resp);
    }
}
