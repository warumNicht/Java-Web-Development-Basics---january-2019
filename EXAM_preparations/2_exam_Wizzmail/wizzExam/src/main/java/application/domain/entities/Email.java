package application.domain.entities;

import application.domain.entities.enums.Flag;

import javax.mail.Flags;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "emails")
public class Email extends BaseEntity{
    @Column(name = "subject",nullable = false,length = 50)
    private String subject;

    @Column(name = "message",
    length = 300)
    private String message;

    @Column(name = "attachment",
           length = 250)
    private String attachment;

    @Column(name = "send_date")
    private Date sendDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "flag",nullable = false)
    private Flag flag;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id",
    nullable = false)
    private User sender;

    @ManyToMany()
    @JoinTable(name = "emails_recipients",
            joinColumns =@JoinColumn(name = "email_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "recipient_id",referencedColumnName = "id"))
    private List<User> recipients;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<User> recipients) {
        this.recipients = recipients;
    }
}
