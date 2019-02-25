package pandaApp.domain.entities;

import pandaApp.domain.entities.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "packages")
public class Package extends BaseEntity {
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "weight",nullable = false)
    private Double weight;

    @Column(name = "shipping_address",nullable = false)
    private String shippingAddress;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;

    @Column(name = "estimated_delivery_date")
    private LocalDateTime estimatedDeliveryDate;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id", nullable = false)
    private User recipient;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
