package emplRegisterApp.repositories;

import emplRegisterApp.domain.entities.Employee;

import java.math.BigDecimal;

public interface EmployeeRepository extends GenericRepository<Employee,String> {

    BigDecimal getTotalEmployeesSalaries();

    Double getAverageEmployeesSalary();
}
