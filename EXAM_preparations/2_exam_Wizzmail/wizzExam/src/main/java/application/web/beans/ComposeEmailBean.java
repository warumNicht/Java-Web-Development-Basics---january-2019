package application.web.beans;

import application.domain.entities.enums.Flag;
import application.domain.models.bindingModels.EmailSComposeBindingModel;
import application.domain.models.serviceModels.EmailServiceModel;
import application.domain.models.serviceModels.UserServiceModel;
import application.services.EmailService;
import application.services.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ComposeEmailBean {
    private UserService userService;
    private EmailService emailService;
    private ModelMapper modelMapper;

    private EmailSComposeBindingModel emailSComposeBindingModel;

    public ComposeEmailBean() {
    }

    @Inject
    public ComposeEmailBean(UserService userService, EmailService emailService, ModelMapper modelMapper) {
        this.userService = userService;
        this.emailService = emailService;
        this.modelMapper = modelMapper;
        this.emailSComposeBindingModel=new EmailSComposeBindingModel();
    }

    public EmailSComposeBindingModel getEmailSComposeBindingModel() {
        return emailSComposeBindingModel;
    }

    public void send() throws IOException {

        List<String> emails = Arrays.stream(this.emailSComposeBindingModel.getRecipients().split("@wizmail.bg\\s*;\\s*"))
                .collect(Collectors.toList());
        EmailServiceModel email =this.createEmail();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        UserServiceModel sender = this.getCurrentUser(externalContext);

        email.setSender(sender);
//        sender.getSentEmails().add(email);


        for (String curEmail : emails) {
            UserServiceModel currentReceiver = this.userService.findByUsername(curEmail);
            email.getRecipients().add(currentReceiver);
//            currentReceiver.getReceivedEmails().add(email);
//            this.userService.update(currentReceiver);
    }

        email.setSendDate(new Date());
        email.setFlag(Flag.sent);
        this.emailService.sendEmail(email);

 //       this.userService.update(sender);
//        for (String curEmail : emails) {
//            UserServiceModel currentReceiver = this.userService.findByUsername(curEmail);
//            currentReceiver.getReceivedEmails().add(email);
//            this.userService.update(currentReceiver);
//        }
        externalContext.redirect("/faces/view/mail.xhtml");

    }

    public void save() throws IOException {
        List<String> emails = Arrays.stream(this.emailSComposeBindingModel.getRecipients().split("@wizmail.bg\\s*;\\s*"))
                .collect(Collectors.toList());
        EmailServiceModel email =this.createEmail();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        UserServiceModel sender = this.getCurrentUser(externalContext);

        email.setSender(sender);
        sender.getSentEmails().add(email);

        for (String curEmail : emails) {
            UserServiceModel currentReceiver = this.userService.findByUsername(curEmail);
            email.getRecipients().add(currentReceiver);
        }

        email.setFlag(Flag.draft);
        this.emailService.sendEmail(email);

        externalContext.redirect("/faces/view/mail.xhtml");
    }

    private UserServiceModel getCurrentUser(ExternalContext externalContext) {
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String senderUsername = (String) session.getAttribute("username");
        UserServiceModel sender = this.userService.findByUsername(senderUsername);
        return sender;
    }


    private EmailServiceModel createEmail() {
        String attachment = this.emailSComposeBindingModel.getAttachment();
        String message = this.emailSComposeBindingModel.getMessage();
        String subject = this.emailSComposeBindingModel.getSubject();

        EmailServiceModel email =new EmailServiceModel(subject,message,attachment);
        return email;
    }

    public void delete(String id) throws IOException {
        EmailServiceModel email = this.emailService.findById(id);
        email.setFlag(Flag.trash);
        this.emailService.updateEmail(email);

        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("/faces/view/mail.xhtml");
    }

    public void restore(String id) throws IOException {
        EmailServiceModel email = this.emailService.findById(id);
        email.setFlag(Flag.sent);
        this.emailService.updateEmail(email);

        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("/faces/view/mail.xhtml");
    }

}
