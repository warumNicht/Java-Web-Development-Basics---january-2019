package metube.web.filters;

import metube.domain.models.binding.TubeAllBindingModel;
import metube.services.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter("/tubes/all")
public class TubeAllFilter implements Filter {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeAllFilter(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        List<TubeAllBindingModel> tubeAllBindingModels = this.tubeService.findAllTubes()
                .stream()
                .map(tubeService -> this.modelMapper.map(tubeService, TubeAllBindingModel.class))
                .collect(Collectors.toList());

        request.setAttribute("allTubesBindingModel",tubeAllBindingModels);
        chain.doFilter(request,response);
    }
}
