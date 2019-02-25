package chushkaApp.web.servlets;


import chushkaApp.domain.models.view.AllProductsViewModel;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private static final String INDEX_HTML_FILE_PATH =
            "D:\\6. Java WEB\\11 Java EE Servlet API 4.0\\exercises\\chushka\\src\\main\\resources\\views\\index.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper mapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper mapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader
                .readHtmlFile(INDEX_HTML_FILE_PATH)
                .replace("{{listItems}}",this.formatViewItems());

        resp.getWriter().write(htmlFileContent);
    }

    private String formatViewItems(){
        List<AllProductsViewModel> allProductsViewModels = this.productService.findAllProducts().stream()
                .map(productServiceModel -> this.mapper.map(productServiceModel, AllProductsViewModel.class))
                .collect(Collectors.toList());

        StringBuilder items=new StringBuilder();
        allProductsViewModels.stream()
                .forEach(product->{
                    try {
                        items.append(String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>",
                                URLEncoder.encode( product.getName(),"utf-8"),product.getName()));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });
        return items.toString();
    }
}
