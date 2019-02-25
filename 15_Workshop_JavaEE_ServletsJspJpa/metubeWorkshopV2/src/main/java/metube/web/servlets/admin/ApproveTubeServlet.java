package metube.web.servlets.admin;

import metube.domain.enums.TubeStatus;
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

@WebServlet("/admin/tube/approve/*")
public class ApproveTubeServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper mapper;

    @Inject
    public ApproveTubeServlet(TubeService tubeService, ModelMapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] urlTokens = req.getRequestURI().split("/");
        String tubeId = urlTokens[urlTokens.length - 1];

        TubeServiceModel tubeServiceModel = this.tubeService.findById(tubeId);
        tubeServiceModel.setStatus(TubeStatus.Approved);

        TubeDetailsViewModel tubeDetailsViewModel = this.mapper.map(tubeServiceModel, TubeDetailsViewModel.class);
        this.tubeService.updateTube(tubeServiceModel);

        resp.sendRedirect("/admin/tube/pending");
    }
}
