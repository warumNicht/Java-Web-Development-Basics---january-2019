package metube.domain.models.binding;

public class TubeUploadBindingModel {
    private String title;
    private String author;
    private String youTubeLink;
    private String description;
    private String uploader;

    public TubeUploadBindingModel(String title, String author,
                                  String youTubeLink, String description
            , String uploader) {
        this.title = title;
        this.author = author;
        this.youTubeLink = youTubeLink;
        this.description = description;
        this.uploader=uploader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
