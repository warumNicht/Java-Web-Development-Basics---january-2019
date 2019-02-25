package metube.domain.entities;

import metube.domain.enums.TubeStatus;

import javax.persistence.*;

@Entity
@Table(name = "tubes")
public class Tube extends BaseEntity{
    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "you_tube_id",nullable = false,updatable = false)
    private String youTubeId;

    @Column(name = "views")
    private long views;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status",nullable = false)
    private TubeStatus status;

    @ManyToOne
    @JoinColumn(name = "uploader_id",referencedColumnName = "id",nullable = false)
    private User uploader;

    public Tube() {
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

    public void setViews(long views) {
        this.views = views;
    }

    public TubeStatus getStatus() {
        return status;
    }

    public void setStatus(TubeStatus status) {
        this.status = status;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
