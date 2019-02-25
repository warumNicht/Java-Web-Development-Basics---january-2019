package application.domain.entities;

import application.domain.entities.enums.ChannelType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "channels")
public class Channel extends BaseEntity {
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private ChannelType type;

    @Column(name = "tags")
    private String tags;

    @ManyToMany(mappedBy = "followedChannels",fetch = FetchType.EAGER)
    private Set<User>  followers ;

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

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }
}
