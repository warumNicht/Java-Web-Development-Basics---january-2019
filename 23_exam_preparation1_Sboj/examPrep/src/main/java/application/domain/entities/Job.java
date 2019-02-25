package application.domain.entities;

import application.domain.entities.enums.Sector;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "sector",nullable = false)
    private Sector sector;

    @Column(name = "profession",nullable = false)
    private String profession;

    @Column(name = "salary",nullable = false)
    private BigDecimal salary;

    @Column(name = "description",nullable = false,
    columnDefinition = "TEXT")
    private String description;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
