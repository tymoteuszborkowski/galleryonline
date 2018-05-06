package pl.tymoteuszborkowski.galleryonline.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private long id;
    private long userId;
    private String path;

    public Photo(long userId, String path) {
        this.userId = userId;
        this.path = path;
    }

    public Photo(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return id == photo.id &&
                userId == photo.userId &&
                Objects.equals(path, photo.path);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, path);
    }
}
