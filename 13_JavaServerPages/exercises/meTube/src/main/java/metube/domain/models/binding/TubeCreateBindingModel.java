package metube.domain.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TubeCreateBindingModel {
    private String name;
    private String description;
    private String youTubeLink;
    private String uploader;

    public TubeCreateBindingModel() {
    }

    public TubeCreateBindingModel(String name, String description, String youTubeLink, String uploader) {
        this.name = name;
        this.description = description;
        this.youTubeLink = youTubeLink;
        this.uploader = uploader;
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
