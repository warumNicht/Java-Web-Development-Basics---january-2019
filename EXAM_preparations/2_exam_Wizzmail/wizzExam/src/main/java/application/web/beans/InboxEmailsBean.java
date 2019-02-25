package application.web.beans;

import application.domain.entities.enums.Flag;
import application.domain.models.serviceModels.UserServiceModel;
import application.domain.models.viewModels.EmailViewModel;
import application.services.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class InboxEmailsBean {

    private UserService userService;
    private ModelMapper modelMapper;

    public InboxEmailsBean() {
    }

    @Inject
    public InboxEmailsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<EmailViewModel> getInboxEmails(){
        UserServiceModel receiver=this.getCurrentUser();

        List<EmailViewModel> emailViewModels = receiver.getReceivedEmails().stream()
                .filter(e->e.getFlag().equals(Flag.sent)||e.getFlag().equals(Flag.read))
                .map(e -> this.modelMapper.map(e, EmailViewModel.class))
                .collect(Collectors.toList());
        return emailViewModels;
    }

    public List<EmailViewModel> getSentEmails(){
        UserServiceModel receiver=this.getCurrentUser();

        List<EmailViewModel> emailViewModels = receiver.getSentEmails().stream()
                .filter(e->e.getFlag().equals(Flag.sent)||e.getFlag().equals(Flag.read))
                .map(e -> this.modelMapper.map(e, EmailViewModel.class))
                .collect(Collectors.toList());
        return emailViewModels;
    }

    public List<EmailViewModel> getDraftEmails(){
        UserServiceModel receiver=this.getCurrentUser();

        List<EmailViewModel> emailViewModels = receiver.getSentEmails().stream()
                .filter(e->e.getFlag().equals(Flag.draft))
                .map(e -> this.modelMapper.map(e, EmailViewModel.class))
                .collect(Collectors.toList());
        return emailViewModels;
    }

    public List<EmailViewModel> getTrashEmails(){
        UserServiceModel receiver=this.getCurrentUser();

        List<EmailViewModel> emailViewModels = receiver.getReceivedEmails().stream()
                .filter(e->e.getFlag().equals(Flag.trash))
                .map(e -> this.modelMapper.map(e, EmailViewModel.class))
                .collect(Collectors.toList());

        return emailViewModels;
    }


    public long getUnreadEmails(){
        long count = this.getInboxEmails().stream()
                .filter(e -> e.getFlag().equals(Flag.sent))
                .count();
        return count;
    }



    private UserServiceModel getCurrentUser() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String senderUsername = (String) session.getAttribute("username");
        UserServiceModel receiver = this.userService.findByUsername(senderUsername);
        return receiver;
    }
}
