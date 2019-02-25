package metube.domain.models.service;

import metube.domain.enums.TubeStatus;

public class TubeServiceModel {
    private String id;
    private String title;
    private String author;
    private String description;
    private String youTubeId;
    private long views;
    private TubeStatus status;
    private UserServiceModel uploader;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYouTubeId() {
        return youTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }

    public long getViews() {
        return views;
    }

    public TubeStatus getStatus() {
        return status;
    }

    public void setStatus(TubeStatus status) {
        this.status = status;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public UserServiceModel getUploader() {
        return uploader;
    }

    public void setUploader(UserServiceModel uploader) {
        this.uploader = uploader;
    }
}
