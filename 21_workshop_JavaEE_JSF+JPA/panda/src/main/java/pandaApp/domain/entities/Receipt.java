package pandaApp.domain.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
public class Receipt extends BaseEntity {
    @Column(name = "fee",nullable = false)
    private BigDecimal fee;

    @Column(name = "issued_on", nullable = false)
    private LocalDateTime issuedOn;

    @ManyToOne
    @JoinColumn(name = "recipient_id",
    referencedColumnName = "id",
    nullable = false)
    private User recipient;

    @OneToOne
    @JoinColumn(name = "package_id",
            referencedColumnName = "id",
            nullable = false)
    private Package aPackage;



    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public LocalDateTime getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
