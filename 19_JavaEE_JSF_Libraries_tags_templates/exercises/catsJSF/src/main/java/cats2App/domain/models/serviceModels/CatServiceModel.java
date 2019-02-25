package cats2App.domain.models.serviceModels;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class CatServiceModel {
    private String id;

    @NotNull
    @Size(min = 2,max = 10)
    private String name;

    @Size(min = 5,max = 20)
    private String breed;

    private String color;

    @Min(value = 1)
    @Max(value = 31)
    private Integer age;

    private String gender;

    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotNull
    @NotEmpty
    private Date addedOn;

    @NotNull
    @NotEmpty
    private boolean hasPassport;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
