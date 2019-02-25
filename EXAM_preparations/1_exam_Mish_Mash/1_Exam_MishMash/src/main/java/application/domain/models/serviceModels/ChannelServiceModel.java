package application.domain.models.serviceModels;

import application.domain.entities.enums.ChannelType;

import java.util.LinkedHashSet;
import java.util.Set;

public class ChannelServiceModel {
    private String id;
    private String name;
    private String description;
    private ChannelType type;
    private String tags;
    private Set<UserServiceModel> followers;

    public ChannelServiceModel() {
        this.followers=new LinkedHashSet<>();
    }

    public ChannelServiceModel( String name, String description, ChannelType type, String tags) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.tags = tags;
        this.followers=new LinkedHashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChannelType getType() {
        return type;
    }

    public void setType(ChannelType type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Set<UserServiceModel> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserServiceModel> followers) {
        this.followers = followers;
    }
}
