package pl.tymoteuszborkowski.galleryonline.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tymoteuszborkowski.galleryonline.model.Photo;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {

    List<Photo> findAll();

    List<Photo> findAllByUserId(long userId);
}
