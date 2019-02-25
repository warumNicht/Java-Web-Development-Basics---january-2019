package application.domain.models.serviceModels;

import application.domain.entities.enums.Flag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailServiceModel {
    private String id;
    private String subject;
    private String message;
    private String attachment;
    private Date sendDate;
    private Flag flag;
    private UserServiceModel sender;
    private List<UserServiceModel> recipients;

    public EmailServiceModel() {
        this.recipients=new ArrayList<>();
    }

    public EmailServiceModel(String subject, String message, String attachment) {
        this.subject = subject;
        this.message = message;
        this.attachment = attachment;
        this.recipients=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public UserServiceModel getSender() {
        return sender;
    }

    public void setSender(UserServiceModel sender) {
        this.sender = sender;
    }

    public List<UserServiceModel> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<UserServiceModel> recipients) {
        this.recipients = recipients;
    }
}
