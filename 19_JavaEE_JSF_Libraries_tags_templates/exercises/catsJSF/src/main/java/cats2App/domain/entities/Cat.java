package cats2App.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cats")
public class Cat extends BaseEntity{
    @Column(name = "name",nullable = false,length = 10)
    private String name;

    @Column(name = "breed",nullable = false,length = 20)
    private String breed;

    @Column(name = "color")
    private String color;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Temporal(TemporalType.DATE)
    @Column(name = "added_on", nullable = false)
    private Date addedOn;

    @Column(name = "has_passport", nullable = false)
    private boolean hasPassport;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public boolean isHasPassport() {
        return hasPassport;
    }

    public void setHasPassport(boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}
