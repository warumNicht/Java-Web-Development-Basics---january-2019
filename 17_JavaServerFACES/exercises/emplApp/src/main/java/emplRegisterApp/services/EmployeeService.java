package emplRegisterApp.services;

import emplRegisterApp.domain.models.serviceModels.EmployeeServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {

    boolean saveEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> findAllEmployees();

    boolean removeEmployee(String id);

    BigDecimal getTotalEmployeesSalaries();

    Double getAverageEmployeesSalary();
}
