package application.web.servlets;

import application.domain.entities.enums.ChannelType;
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
import java.util.ArrayList;

@WebServlet("/channels/create")
public class CreateChannelServlet extends HttpServlet {
    private final ChannelService channelService;
    @Inject
    public CreateChannelServlet(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Override
    public void init() throws ServletException {
        this.getServletContext().setAttribute("radio", "Game, Motivation, Lessons, Radio, Other");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/createCh.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String tags = req.getParameter("tags");
        String type = req.getParameter("type");
        ChannelServiceModel channelServiceModel =
                new ChannelServiceModel(name, description, ChannelType.valueOf(type), tags);

        this.channelService.addChannel(channelServiceModel);
        resp.sendRedirect("/home");
    }
}
