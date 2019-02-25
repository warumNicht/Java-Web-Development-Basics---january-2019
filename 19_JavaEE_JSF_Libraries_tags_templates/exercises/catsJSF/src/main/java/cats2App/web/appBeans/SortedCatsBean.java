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
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class SortedCatsBean {
    private CatService catService;
    private ModelMapper modelMapper;

    public SortedCatsBean() {
    }

    @Inject
    public SortedCatsBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    public void getSortingCriteria(String criteria) throws IOException {

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        if(criteria.isEmpty()){
            externalContext.redirect("/view/all-cats.xhtml");
            return;
        }
        List<CatViewModel> catViewModelList = this.catService.getSortedCats(criteria).stream()
                .map(c -> this.modelMapper.map(c, CatViewModel.class))
                .collect(Collectors.toList());

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("sorted", catViewModelList);

        externalContext.redirect("/view/all-cats.xhtml");
    }

    public List<CatViewModel> getSortedCats(String criteria) {

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = request.getSession();
        List<CatViewModel> sorted = (List<CatViewModel>) session.getAttribute("sorted");

        return sorted;
    }
}
