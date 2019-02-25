package metube.web.servlets.admin;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubePendingView;
import metube.domain.models.view.UserHomeTubesViewModel;
import metube.services.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/tube/pending")
public class PendingServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper mapper;

    @Inject
    public PendingServlet(TubeService tubeService, ModelMapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubePendingView> pendingTubes = this.tubeService.getPendingTubes()
                .stream()
                .map(t -> {
                    TubePendingView pending = this.mapper.map(t, TubePendingView.class);
                    pending.setUploader(t.getUploader().getUsername());
                    return pending;
                })
                .collect(Collectors.toList());
        req.setAttribute("pending",pendingTubes);

        req.getRequestDispatcher("/jsp/pending.jsp").forward(req, resp);
    }
}
