package pl.tymoteuszborkowski.galleryonline.model;

import java.util.Arrays;
import java.util.Objects;

public class Photo {

    private String path;
    private byte[] bytes;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(path, photo.path) &&
                Arrays.equals(bytes, photo.bytes);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(path);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }
}
