package chushkaApp.web.servlets;

import chushkaApp.domain.entities.Type;
import chushkaApp.domain.models.service.ProductServiceModel;
import chushkaApp.service.ProductService;
import chushkaApp.util.HtmlReader;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    private static final String CREATE_HTML_FILE_PATH =
            "D:\\6. Java WEB\\11 Java EE Servlet API 4.0\\exercises\\chushka\\src\\main\\resources\\views\\createProduct.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader.readHtmlFile(CREATE_HTML_FILE_PATH)
                .replace("{{typeOptions}}",this.formatTypeOptions());
        resp.getWriter().write(htmlFileContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel productServiceModel=new ProductServiceModel();
        productServiceModel.setName(req.getParameter("name"));
        productServiceModel.setDescription(req.getParameter("description"));
        productServiceModel.setType(req.getParameter("type"));

        this.productService.saveProduct(productServiceModel);
        resp.sendRedirect("/");
    }

    private String formatTypeOptions(){
        StringBuilder options=new StringBuilder();
        Arrays.stream(Type.values()).forEach(o->{
            options.append(String.format("<option value=\"%s\">%s</option>",o.name(),o.name()));
        });
        return options.toString();
    }
}
