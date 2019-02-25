package application.services;

import application.domain.entities.Channel;
import application.domain.models.serviceModels.ChannelServiceModel;
import application.repositories.ChannelRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public ChannelServiceImpl(ChannelRepository channelRepository, UserService userService, ModelMapper modelMapper) {
        this.channelRepository = channelRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @Override
    public ChannelServiceModel findById(String id){
        Channel channel = this.channelRepository.findById(id).orElse(null);
        return this.modelMapper.map(channel,ChannelServiceModel.class);
    }

    @Override
    public boolean addChannel(ChannelServiceModel channelServiceModel) {
        try {
            Channel channel = this.modelMapper.map(channelServiceModel, Channel.class);
            this.channelRepository.save(channel);
            return true;
        }catch (Exception e){
            return true;
        }
    }
    private List<ChannelServiceModel> getNotBelonging(String username){
        List<Channel> chan = this.channelRepository.getNotBelongingToUser(username);
        List<ChannelServiceModel> collect = chan.stream()
                .map(c -> {
                    ChannelServiceModel channelServiceModel = this.modelMapper.map(c, ChannelServiceModel.class);
                    return channelServiceModel;
                })
                .collect(Collectors.toList());
        return collect;
    }
    @Override
    public List<ChannelServiceModel> getSuggested(String username) {
        List<ChannelServiceModel> notBelonging = this.getNotBelonging(username);
        List<ChannelServiceModel> usersChannels = this.userService.getUsersChannels(username);

        List<ChannelServiceModel> suggested=new ArrayList<>();

        for (ChannelServiceModel otherChannel : notBelonging) {
            boolean isContained=false;
            for (int i = 0; i < usersChannels.size(); i++) {
                ChannelServiceModel userChannel = usersChannels.get(i);
                if(this.otherContainsTagFromUser(otherChannel,userChannel)){
                    isContained=true;
                    break;
                }
            }
            if(isContained){
                suggested.add(otherChannel);
            }
        }
        return suggested;
    }

    @Override
    public List<ChannelServiceModel> getSeeOther(String username) {
        List<ChannelServiceModel> notBelonging = this.getNotBelonging(username);
        List<ChannelServiceModel> usersChannels = this.userService.getUsersChannels(username);
        List<ChannelServiceModel> others=new ArrayList<>();

        for (ChannelServiceModel otherChannel : notBelonging) {
            boolean isContained=false;
            for (int i = 0; i < usersChannels.size(); i++) {
                ChannelServiceModel userChannel = usersChannels.get(i);
                if(this.otherContainsTagFromUser(otherChannel,userChannel)){
                    isContained=true;
                    break;
                }
            }
            if(isContained==false){
                others.add(otherChannel);
            }
        }
        return others;
    }

    private boolean otherContainsTagFromUser(ChannelServiceModel otherChannel, ChannelServiceModel userChannel) {
        String[] otherTags = otherChannel.getTags().split(", ");
        String[] userTags = userChannel.getTags().split(", ");
        for (String otherTag : otherTags) {
            for (int i = 0; i < userTags.length; i++) {
                if(otherTag.equals(userTags[i])){
                    return true;
                }
            }
        }
        return false;
    }
}
