package pl.tymoteuszborkowski.galleryonline.model;

import java.util.List;
import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String password;
    private List<Photo> uploadedPhotos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Photo> getUploadedPhotos() {
        return uploadedPhotos;
    }

    public void setUploadedPhotos(List<Photo> uploadedPhotos) {
        this.uploadedPhotos = uploadedPhotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(uploadedPhotos, user.uploadedPhotos);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, uploadedPhotos);
    }
}
