package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.domain.services.CatService;
import fdmc.util.HtmlReader;
import fdmc.util.JsonParserImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/script")
public class JavaScriptServlet extends HttpServlet {
    private final static String INDEX_HTML_FILE_PATH="D:\\6. Java WEB\\13_JavaServerPages\\lab\\labJSP\\src\\main\\resources\\views\\scr.html";
    private final HtmlReader reader;
    private final CatService catService;
    private final JsonParserImpl parser;

    @Inject
    public JavaScriptServlet(HtmlReader reader, CatService catService, JsonParserImpl jsonParser) {
        this.reader = reader;
        this.catService=catService;
        this.parser=jsonParser;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cat> cats = this.catService.getAll();
        String json = this.parser.toJson(cats);
        String readHtmlFile = this.reader.readHtmlFile(INDEX_HTML_FILE_PATH);
        resp.getWriter().write(readHtmlFile);
    }
}
