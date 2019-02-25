package cats2App.web.appBeans;

import cats2App.domain.models.viewModels.CatViewModel;
import cats2App.services.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllCatsBean {
    private CatService catService;
    private ModelMapper modelMapper;



    public AllCatsBean() {

    }

    @Inject
    public AllCatsBean(CatService catService, ModelMapper modelMapper) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    public List<CatViewModel> getAllCatsViews() {

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = request.getSession();
        List<CatViewModel> sorted = (List<CatViewModel>) session.getAttribute("sorted");
        session.setAttribute("sorted",null);

        if(sorted!=null){
            return sorted;
        }

        List<CatViewModel> catViewModels = this.catService.findAll().stream()
                .map(c -> this.modelMapper.map(c, CatViewModel.class))
                .collect(Collectors.toList());
        return catViewModels;
    }
}
