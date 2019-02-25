package application.services;

import application.domain.entities.Email;
import application.domain.models.serviceModels.EmailServiceModel;
import application.domain.models.viewModels.EmailViewModel;
import application.repositories.EmailRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmailServiceImpl(EmailRepository emailRepository, ModelMapper modelMapper) {
        this.emailRepository = emailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void sendEmail(EmailServiceModel emailServiceModel) {
        Email email = this.modelMapper.map(emailServiceModel, Email.class);

        this.emailRepository.save(email);
    }

    @Override
    public EmailServiceModel findById(String id) {
        Email email = this.emailRepository.findById(id).orElse(null);

        return this.modelMapper.map(email,EmailServiceModel.class);
    }

    @Override
    public void updateEmail(EmailServiceModel emailServiceModel) {
        Email email = this.modelMapper.map(emailServiceModel, Email.class);
        this.emailRepository.update(email);
    }
}
