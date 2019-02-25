package emplRegisterApp.web.managedBeans;

import emplRegisterApp.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Named("employeeStatistics")
@RequestScoped
public class EmployeeStatisticsBean {
    private EmployeeService employeeService;
    private DecimalFormat decimalFormat;

    public EmployeeStatisticsBean() {
    }

    @Inject
    public EmployeeStatisticsBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.decimalFormat=new DecimalFormat("0.00");
    }

    public String getTotalSalary(){
        BigDecimal totalEmployeesSalaries = this.employeeService.getTotalEmployeesSalaries();
        return this.decimalFormat.format(totalEmployeesSalaries);

    }

    public String getAverageSalary(){
        Double averageEmployeesSalary = this.employeeService.getAverageEmployeesSalary();
        return this.decimalFormat.format(averageEmployeesSalary);
    }
}
