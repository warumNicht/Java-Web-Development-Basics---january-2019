package application.web.servlets;

import application.domain.models.viewModels.AllChannelsViewModel;
import application.services.UserService;
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

@WebServlet("/channels/followed")
public class AllChannels extends HttpServlet {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public AllChannels(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        List<AllChannelsViewModel> viewModels = this.userService.getUsersChannels(username).stream()
                .map(c -> {
                    AllChannelsViewModel viewModel = this.modelMapper.map(c, AllChannelsViewModel.class);
                    viewModel.setFollowersCount(c.getFollowers().size());
                    return viewModel;
                })
                .collect(Collectors.toList());

        req.setAttribute("all",viewModels);
        req.getRequestDispatcher("/jsp/allChannels.jsp").forward(req,resp);
    }
}
