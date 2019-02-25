package chushkaApp.web.servlets;

import chushkaApp.domain.models.service.ProductServiceModel;
import chushkaApp.domain.models.view.ProductDetailViewModel;
import chushkaApp.service.ProductService;
import chushkaApp.util.HtmlReader;
import chushkaApp.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class ProductDetailServlet extends HttpServlet {
    private static final String DETAILS_HTML_FILE_PATH =
            "D:\\6. Java WEB\\11 Java EE Servlet API 4.0\\exercises\\chushka\\src\\main\\resources\\views\\detailsProduct.html";

    private final ProductService productService;
    private final ModelMapper mapper;
    private final HtmlReader htmlReader;

    @Inject
    public ProductDetailServlet(ProductService productService, ModelMapper mapper, HtmlReader htmlReader) {
        this.productService = productService;
        this.mapper = mapper;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("name");
        ProductServiceModel product = this.productService.findProductByName(productName);
        ProductDetailViewModel detailViewModel = this.mapper.map(product, ProductDetailViewModel.class);

        String htmlFileContent = this.htmlReader.readHtmlFile(DETAILS_HTML_FILE_PATH)
                .replace("{{productName}}",detailViewModel.getName()==null ? "not entered" : detailViewModel.getName())
                .replace("{{productDescription}}",
                        detailViewModel.getDescription()==null|| detailViewModel.getDescription().equals("") ? "not entered" : detailViewModel.getDescription())
                .replace("{{productType}}",
                        detailViewModel.getType()==null|| detailViewModel.getType().equals("") ? "not entered" : detailViewModel.getType());

        resp.getWriter().write(htmlFileContent);
    }
}
