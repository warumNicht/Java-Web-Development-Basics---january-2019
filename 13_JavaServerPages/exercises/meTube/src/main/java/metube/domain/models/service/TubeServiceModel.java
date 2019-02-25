package metube.domain.models.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TubeServiceModel {
    private String id;
    @NotNull
    @Size(min = 2,message = "Name must have min 2 letters!")
    private String name;

    @NotNull
    @Size(min = 2,message = "Description must have min 2 letters!")
    private String description;

    @Pattern(regexp = "https:\\/\\/www\\.youtube\\.com\\/watch\\?.*"
            ,message = "Url must begin with \"https://www.youtube.com/watch\"!")
    private String youTubeLink;

    @NotNull
    @Size(min = 2,message = "Uploader must have min 2 letters!")
    private String uploader;

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
