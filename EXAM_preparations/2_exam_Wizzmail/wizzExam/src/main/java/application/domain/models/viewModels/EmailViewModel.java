package application.domain.models.viewModels;

import application.domain.entities.enums.Flag;
import application.domain.models.serviceModels.UserServiceModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailViewModel {
    private String id;
    private String subject;
    private String message;
    private String attachment;
    private Date sendDate;
    private Flag flag;


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
}
