package emplRegisterApp.web.managedBeans;

import emplRegisterApp.domain.models.binding.EmployeeRegisterBindingModel;
import emplRegisterApp.domain.models.serviceModels.EmployeeServiceModel;
import emplRegisterApp.services.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeRegisterBean {
    private EmployeeRegisterBindingModel employeeRegisterBindingModel;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeRegisterBean() {
        this.employeeRegisterBindingModel = new EmployeeRegisterBindingModel();
    }

    @Inject
    public EmployeeRegisterBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    public EmployeeRegisterBindingModel getEmployeeRegisterBindingModel() {
        return employeeRegisterBindingModel;
    }

    public void setEmployeeRegisterBindingModel(EmployeeRegisterBindingModel employeeRegisterBindingModel) {
        this.employeeRegisterBindingModel = employeeRegisterBindingModel;
    }

    public void register() throws IOException {
        EmployeeServiceModel serviceModel = this.modelMapper.map(this.employeeRegisterBindingModel, EmployeeServiceModel.class);
        this.employeeService.saveEmployee(serviceModel);

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        externalContext.redirect("/");
    }
}
