package metube.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tubes")
public class Tube extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "youtube_link")
    private String youTubeLink;

    @Column(name = "uploader")
    private String uploader;

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

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
