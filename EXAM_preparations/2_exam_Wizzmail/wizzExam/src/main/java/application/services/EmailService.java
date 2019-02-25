package application.services;

import application.domain.models.serviceModels.EmailServiceModel;

public interface EmailService {
    void sendEmail(EmailServiceModel emailServiceModel);

    EmailServiceModel findById(String id);

    void updateEmail(EmailServiceModel emailServiceModel);
}
