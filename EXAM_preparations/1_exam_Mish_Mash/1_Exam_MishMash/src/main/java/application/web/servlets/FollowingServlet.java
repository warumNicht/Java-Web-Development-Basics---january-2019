package application.web.servlets;

import application.domain.models.serviceModels.ChannelServiceModel;
import application.services.ChannelService;
import application.services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/channels/following/*")
public class FollowingServlet extends HttpServlet {
    private final UserService userService;
    private final ChannelService channelService;

    @Inject
    public FollowingServlet(UserService userService, ChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] uriTokens = req.getRequestURI().split("/");
        String channelId = uriTokens[uriTokens.length - 1];
        String username = (String) req.getSession().getAttribute("username");

        ChannelServiceModel channelById = this.channelService.findById(channelId);

        this.userService.addChannelToUser(username,channelById);

        resp.sendRedirect("/home");
    }
}
