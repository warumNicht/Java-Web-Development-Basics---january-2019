package fdmc.web.servlets;

import fdmc.domain.services.CatService;
import fdmc.domain.services.CatServiceImpl;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

@WebServlet("")
public class IndexServlet extends HttpServlet {
    private final static String INDEX_HTML_FILE_PATH=
            "D:\\6. Java WEB\\13_JavaServerPages\\lab\\labJSP\\src\\main\\resources\\views\\index.html";
    private final HtmlReader reader;


    @Inject
    public IndexServlet(HtmlReader reader) {
        super();
        this.reader = reader;
    }

    @Override
    public void init() throws ServletException {
        this.getServletContext().setAttribute("cats",new LinkedHashMap<>());
        this.getServletContext().setAttribute("catsDB",new CatServiceImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name","Pe6o");
        req.getRequestDispatcher("/views/index2.jsp").forward(req,resp);
    }
}
