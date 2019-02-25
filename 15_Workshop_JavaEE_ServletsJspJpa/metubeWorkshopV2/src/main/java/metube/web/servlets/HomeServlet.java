package metube.web.servlets;

import metube.domain.models.service.UserServiceModel;
import metube.domain.models.view.UserHomeTubesViewModel;
import metube.domain.models.view.UserHomeViewModel;
import metube.services.TubeService;
import metube.services.UserService;
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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper mapper;

    @Inject
    public HomeServlet(TubeService tubeService, ModelMapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserHomeTubesViewModel> approvedTubes = this.tubeService.findAllApproved().stream()
                .map(t -> this.mapper.map(t, UserHomeTubesViewModel.class))
                .collect(Collectors.toList());
        req.setAttribute("homeApprovedTubes",approvedTubes);
        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }
}
