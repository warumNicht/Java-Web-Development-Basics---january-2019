package pandaApp.web.beans.receipts;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.serviceModels.UserServiceModel;
import pandaApp.domain.models.viewModels.ReceiptViewModel;
import pandaApp.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReceiptAllBean {
    private UserService userService;
    private ModelMapper modelMapper;

    public ReceiptAllBean() {
    }

    @Inject
    public ReceiptAllBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<ReceiptViewModel> findAllReceipts(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String username = (String) session.getAttribute("username");
        UserServiceModel user = this.userService.findByUsername(username).orElse(null);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");


        List<ReceiptViewModel> receipts = user.getReceipts().stream()
                .map(r -> {
                    ReceiptViewModel receiptViewModel = this.modelMapper.map(r, ReceiptViewModel.class);
                    String formatDateTime = r.getIssuedOn().format(formatter);
                    receiptViewModel.setIssuedOn(formatDateTime);
                    return receiptViewModel;
                })
                .collect(Collectors.toList());

        return receipts;
    }
}
