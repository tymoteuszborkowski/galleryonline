package pl.tymoteuszborkowski.galleryonline.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tymoteuszborkowski.galleryonline.model.User;

@Repository("galleryUserRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

}
