package application.repositories;

import application.domain.entities.Channel;

import java.util.List;

public interface ChannelRepository extends GenericRepository <Channel,String> {
    List<Channel> getNotBelongingToUser(String username);
}
