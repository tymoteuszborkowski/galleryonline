package pl.tymoteuszborkowski.galleryonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tymoteuszborkowski.galleryonline.config.spring.UserPrincipal;
import pl.tymoteuszborkowski.galleryonline.model.Photo;
import pl.tymoteuszborkowski.galleryonline.model.User;
import pl.tymoteuszborkowski.galleryonline.repositories.PhotoRepository;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PhotoRepository photoRepository;

    @RequestMapping("/")
    public String showHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "home";
        }

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User loggedUser = userPrincipal.getUser();

        List<Photo> userPhotos;
        if (loggedUser.getRole().equals("ROLE_ADMIN")) {
            userPhotos = photoRepository.findAll();
        } else {
            userPhotos = photoRepository.findAllByUserId(loggedUser.getId());
        }

        model.addAttribute("loggedUser", "Witaj, " + loggedUser.getName());

        if (!userPhotos.isEmpty()) {
            Photo firstPhoto = userPhotos.get(0);
            userPhotos.remove(firstPhoto);
            model.addAttribute("firstPhoto", firstPhoto);
            model.addAttribute("photos", userPhotos);
        }

        return "home";
    }


}
