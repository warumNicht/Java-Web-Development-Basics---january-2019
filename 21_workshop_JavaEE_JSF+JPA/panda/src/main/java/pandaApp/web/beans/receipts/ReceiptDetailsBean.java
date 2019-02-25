package pandaApp.web.beans.receipts;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.serviceModels.ReceiptServiceModel;
import pandaApp.domain.models.serviceModels.UserServiceModel;
import pandaApp.domain.models.viewModels.ReceiptViewModel;
import pandaApp.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class ReceiptDetailsBean {
    private UserService userService;
    private ModelMapper modelMapper;

    public ReceiptDetailsBean() {
    }

    @Inject
    public ReceiptDetailsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public ReceiptViewModel getReceiptDetails(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        String username = (String) session.getAttribute("username");
        UserServiceModel user = this.userService.findByUsername(username).orElse(null);

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String id = request.getParameter("id");

        ReceiptServiceModel receiptServiceModel = user.getReceipts().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        ReceiptViewModel receiptViewModel = this.modelMapper.map(receiptServiceModel, ReceiptViewModel.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        String formatDateTime = receiptServiceModel.getIssuedOn().format(formatter);

        receiptViewModel.setIssuedOn(formatDateTime);

        return receiptViewModel;
    }
}
