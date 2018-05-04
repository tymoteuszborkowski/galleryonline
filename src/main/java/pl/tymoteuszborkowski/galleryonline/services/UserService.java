package pl.tymoteuszborkowski.galleryonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.tymoteuszborkowski.galleryonline.config.spring.UserPrincipal;
import pl.tymoteuszborkowski.galleryonline.model.User;
import pl.tymoteuszborkowski.galleryonline.repositories.UserRepository;

import java.util.Optional;

@Service("userService")
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository galleryUserRepository) {
        this.userRepository = galleryUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));

        if (user.isPresent()) {
            return new UserPrincipal(user.get());
        }
        throw new UsernameNotFoundException(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}