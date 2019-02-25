package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.domain.services.CatService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class CatServlet extends HttpServlet {
    private final CatService catService;
@Inject
    public CatServlet(CatService catService) {
        this.catService = catService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Cat cat=new Cat();
        cat.setName(username);
        this.catService.addCat(cat);
        resp.sendRedirect("/");
    }
}
