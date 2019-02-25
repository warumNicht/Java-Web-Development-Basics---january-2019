package emplRegisterApp.web.managedBeans;

import emplRegisterApp.domain.models.views.EmployeeListViewModel;
import emplRegisterApp.services.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    private List<EmployeeListViewModel> employees;

    public EmployeeListBean() {
    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.findAllEmployees()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeListViewModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }
}
