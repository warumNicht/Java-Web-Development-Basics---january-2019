package emplRegisterApp.services;

import emplRegisterApp.domain.entities.Employee;
import emplRegisterApp.domain.models.serviceModels.EmployeeServiceModel;
import emplRegisterApp.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel) {
        try {
            Employee employee = this.modelMapper.map(employeeServiceModel, Employee.class);
            this.employeeRepository.save(employee);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<EmployeeServiceModel> findAllEmployees() {
        return this.employeeRepository.findAll()
                .stream()
                .map(e->this.modelMapper.map(e,EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeEmployee(String id) {
        try {
            this.employeeRepository.remove(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public BigDecimal getTotalEmployeesSalaries() {
        return this.employeeRepository.getTotalEmployeesSalaries();
    }

    @Override
    public Double getAverageEmployeesSalary() {
        return this.employeeRepository.getAverageEmployeesSalary();
    }
}
