package metube.web.servlets;

import metube.domain.enums.TubeStatus;
import metube.domain.models.binding.TubeUploadBindingModel;
import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.service.UserServiceModel;
import metube.services.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/upload")
public class TubeUploadServlet extends HttpServlet {
    private final ModelMapper mapper;
    private final TubeService tubeService;

    @Inject
    public TubeUploadServlet(ModelMapper mapper, TubeService tubeService) {
        this.mapper = mapper;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeUploadBindingModel tubeModel = (TubeUploadBindingModel) req.getAttribute("uploadTubeModel");
        TubeServiceModel tubeServiceModel = this.mapper.map(tubeModel, TubeServiceModel.class);

        UserServiceModel uploader=new UserServiceModel();
        uploader.setUsername(tubeModel.getUploader());

        String finalId = tubeModel.getYouTubeLink().split("=|&")[1];
        tubeServiceModel.setYouTubeId(finalId);
        tubeServiceModel.setUploader(uploader);
        tubeServiceModel.setStatus(TubeStatus.Pending);

        this.tubeService.uploadTube(tubeServiceModel);

        resp.sendRedirect("/");
    }
}
