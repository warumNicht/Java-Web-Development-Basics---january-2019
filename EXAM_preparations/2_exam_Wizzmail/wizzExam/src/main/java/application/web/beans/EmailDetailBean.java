package application.web.beans;

import application.domain.entities.enums.Flag;
import application.domain.models.serviceModels.EmailServiceModel;
import application.domain.models.serviceModels.UserServiceModel;
import application.domain.models.viewModels.EmailDetailsViewModel;
import application.services.EmailService;
import application.services.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmailDetailBean {
    private EmailService emailService;
    private UserService userService;
    private ModelMapper modelMapper;

    public EmailDetailBean() {
    }
    @Inject
    public EmailDetailBean(EmailService emailService, UserService userService, ModelMapper modelMapper) {
        this.emailService = emailService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public EmailDetailsViewModel getDetails(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String id = request.getParameter("id");
        EmailServiceModel emailServiceModel= this.emailService.findById(id);

        EmailDetailsViewModel detailsViewModel = this.modelMapper.map(emailServiceModel, EmailDetailsViewModel.class);

        detailsViewModel.setSender(emailServiceModel.getSender().getUsername()+"@wizmail.bg");

        List<String> recipientsNames = emailServiceModel.getRecipients().stream()
                .map(r -> r.getUsername())
                .collect(Collectors.toList());

        String concatenatedRecipients = String.join("@wizmail.bg; ", recipientsNames);
        detailsViewModel.setRecipients(concatenatedRecipients+"@wizmail.bg;");

        HttpSession session = (HttpSession) externalContext.getSession(true);
        String receiverUsername = (String) session.getAttribute("username");
        UserServiceModel receiver = this.userService.findByUsername(receiverUsername);

        if(recipientsNames.contains(receiver.getUsername())){
            emailServiceModel.setFlag(Flag.read);
        }
        this.emailService.updateEmail(emailServiceModel);

        return detailsViewModel;
    }
}
