package metube.web.servlets;

import metube.domain.models.binding.TubeCreateBindingModel;
import metube.domain.models.service.TubeServiceModel;
import metube.services.TubeService;
import metube.util.ModelMapper;
import metube.util.ValidationUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Set;

@WebServlet("/tubes/create")
public class TubeCreateServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Inject
    public TubeCreateServlet(TubeService tubeService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create-tube.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBindingModel tubeCreateBindingModel= (TubeCreateBindingModel) req.getAttribute("tubeCreateBindingModel");
        TubeServiceModel tubeServiceModel = this.modelMapper.map(tubeCreateBindingModel, TubeServiceModel.class);
        if(!this.validationUtil.isValid(tubeServiceModel)){
            Set<ConstraintViolation<TubeServiceModel>> violations = this.validationUtil.getViolations(tubeServiceModel);
            req.setAttribute("errors",violations);
            req.getRequestDispatcher("/jsp/errorVisualisation.jsp").forward(req,resp);
            return;
        }
        this.tubeService.saveTube(tubeServiceModel);
        String encode = URLEncoder.encode(tubeServiceModel.getName(), "utf-8");
        resp.sendRedirect("/tubes/details?name="+ encode);
    }
}
