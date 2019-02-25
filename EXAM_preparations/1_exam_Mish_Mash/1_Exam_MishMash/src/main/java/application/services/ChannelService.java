package application.services;

import application.domain.models.serviceModels.ChannelServiceModel;
import application.domain.models.serviceModels.UserServiceModel;

import java.util.List;

public interface ChannelService {
    ChannelServiceModel findById(String id);

    boolean addChannel(ChannelServiceModel channelServiceModel);

    List<ChannelServiceModel> getSuggested(String username);


    List<ChannelServiceModel> getSeeOther(String username);
}
