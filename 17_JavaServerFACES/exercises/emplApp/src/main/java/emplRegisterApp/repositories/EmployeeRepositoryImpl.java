package emplRegisterApp.repositories;

import emplRegisterApp.domain.entities.Employee;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<Employee> findAll() {
        this.entityManager.getTransaction().begin();
        List<Employee> employees = this.entityManager.createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return employees;
    }

    @Override
    public Optional<Employee> findById(String id) {
        try{
            this.entityManager.getTransaction().begin();
            Employee employee = this.entityManager.
                    createQuery("SELECT e FROM Employee e WHERE e.id= :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(employee);

        }catch (Exception e){
            return Optional.empty();
        }finally {
            this.entityManager.getTransaction().commit();
        }
    }

    @Override
    public void remove(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("DELETE FROM Employee e " +
                "WHERE e.id= :id")
                .setParameter("id",id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();

    }

    @Override
    public BigDecimal getTotalEmployeesSalaries() {
        BigDecimal sum = this.entityManager
                .createQuery("SELECT sum(e.salary) FROM Employee e", BigDecimal.class)
                .getSingleResult();


        if(sum==null){
            return BigDecimal.valueOf(0);
        }
        return sum;
    }

    @Override
    public Double getAverageEmployeesSalary() {
        Double averageSalary = this.entityManager
                .createQuery("SELECT avg(e.salary) FROM Employee e", Double.class)
                .getSingleResult();

        BigDecimal result = (BigDecimal) this.entityManager
                .createNativeQuery("SELECT AVG(e.salary) FROM employees AS e ")
                .getSingleResult();

        BigDecimal average2 = result.setScale(2, RoundingMode.HALF_UP);

        if(averageSalary==null){
            return 0.0;
        }
        return averageSalary;
    }
}
