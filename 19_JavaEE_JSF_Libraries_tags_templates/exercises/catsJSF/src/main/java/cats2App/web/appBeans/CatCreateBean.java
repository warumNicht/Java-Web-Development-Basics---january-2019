package cats2App.web.appBeans;

import cats2App.domain.models.bindingModels.CatBindingModel;
import cats2App.domain.models.serviceModels.CatServiceModel;
import cats2App.services.CatService;
import cats2App.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.Set;

@Named
@RequestScoped
public class CatCreateBean {
    private CatBindingModel catBindingModel;
    private ModelMapper modelMapper;
    private CatService catService;
    private ValidationUtil validationUtil;
    private Set<ConstraintViolation<CatServiceModel>> errors;

    public CatCreateBean() {
        this.catBindingModel = new CatBindingModel();
    }

    @Inject
    public CatCreateBean(ModelMapper modelMapper, CatService catService, ValidationUtil validationUtil) {
        this();
        this.modelMapper = modelMapper;
        this.catService = catService;
        this.validationUtil=validationUtil;

    }

    public Set<ConstraintViolation<CatServiceModel>> getErrors() {
        return errors;
    }

    public void setErrors(Set<ConstraintViolation<CatServiceModel>> errors) {
        this.errors = errors;
    }

    public CatBindingModel getCatBindingModel() {
        return catBindingModel;
    }

    public void setCatBindingModel(CatBindingModel catBindingModel) {
        this.catBindingModel = catBindingModel;
    }

    public void registerCat() throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        CatServiceModel catServiceModel = this.modelMapper.map(this.catBindingModel, CatServiceModel.class);
        if(catServiceModel.getColor().isEmpty()){
            catServiceModel.setColor(null);
        }

        if(!this.validationUtil.isValid(catServiceModel)){
            externalContext.redirect("/view/index.xhtml");
            return;
        }
        this.catService.registerCat(catServiceModel);
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        request.getSession().setAttribute("catname",catServiceModel.getName());
        externalContext.redirect("/view/createMore.xhtml");
    }
}
