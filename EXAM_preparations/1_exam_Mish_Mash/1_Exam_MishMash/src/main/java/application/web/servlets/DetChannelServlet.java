package application.web.servlets;

import application.domain.models.serviceModels.ChannelServiceModel;
import application.domain.models.viewModels.DetailChannelsViewModel;
import application.services.ChannelService;
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

@WebServlet("/channels/details/*")
public class DetChannelServlet extends HttpServlet {
    private final ChannelService channelService;
    private final ModelMapper modelMapper;

    @Inject
    public DetChannelServlet(ChannelService channelService, ModelMapper modelMapper) {
        this.channelService = channelService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] uriTokens = req.getRequestURI().split("/");
        String channelId = uriTokens[uriTokens.length - 1];

        String username = (String) req.getSession().getAttribute("username");
        ChannelServiceModel channel = this.channelService.findById(channelId);
        DetailChannelsViewModel detail = this.modelMapper.map(channel, DetailChannelsViewModel.class);
        detail.setFollowersCount(channel.getFollowers().size());
        req.setAttribute("details", detail);
        req.getRequestDispatcher("/jsp/details.jsp").forward(req, resp);
    }
}
