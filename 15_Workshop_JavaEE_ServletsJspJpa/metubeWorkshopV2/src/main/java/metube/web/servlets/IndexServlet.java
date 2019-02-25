package metube.web.servlets;

import metube.domain.models.view.UserHomeTubesViewModel;
import metube.repositories.TubeRepository;
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

@WebServlet("")
public class IndexServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserHomeTubesViewModel> approvedTubes = this.tubeService.findAllApproved()
                .stream()
                .map(t -> this.modelMapper.map(t, UserHomeTubesViewModel.class))
                .collect(Collectors.toList());
        req.setAttribute("approvedTubes",approvedTubes);

        req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }
}
