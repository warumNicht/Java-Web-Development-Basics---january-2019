package metube.web.servlets;

import metube.domain.models.binding.TubeAllBindingModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tubes/all")
public class AllTubesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeAllBindingModel> allTubes=
                (List<TubeAllBindingModel>) req.getAttribute("allTubesBindingModel");

        if(allTubes.isEmpty()){
            req.getRequestDispatcher("/jsp/noTubes.jsp").forward(req,resp);
            return;
        }
        req.getRequestDispatcher("/jsp/all-tubes.jsp").forward(req,resp);
    }
}
