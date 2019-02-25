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
    private final static String INDEX_HTML_FILE_PATH="D:\\6. Java WEB\\NOV web\\9 Java EE\\exercise_Java_EE\\catsExercise\\src\\main\\resources\\views\\index.html";
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

        PrintWriter writer = resp.getWriter();
        writer.println(this.reader.readHtmlFile(INDEX_HTML_FILE_PATH));
    }
}
