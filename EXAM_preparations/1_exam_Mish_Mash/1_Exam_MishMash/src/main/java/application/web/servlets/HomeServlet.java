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
import java.util.List;
import java.util.Set;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private final ChannelService channelService;
    private final UserService userService;


    @Inject
    public HomeServlet(ChannelService channelService, UserService userService) {
        this.channelService = channelService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");

        List<ChannelServiceModel> usersChannels = this.userService.getUsersChannels(username);
        req.setAttribute("your",usersChannels);

        List<ChannelServiceModel> suggested =
                this.channelService.getSuggested(username);
        req.setAttribute("suggested",suggested);

        List<ChannelServiceModel> seeOthers =
                this.channelService.getSeeOther(username);
        req.setAttribute("seeOthers",seeOthers);




        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }
}
