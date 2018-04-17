package pl.tymoteuszborkowski.galleryonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.tymoteuszborkowski.galleryonline.model.User;
import pl.tymoteuszborkowski.galleryonline.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

import static pl.tymoteuszborkowski.galleryonline.constants.FrontentMessages.SUCCESSFUL_REGISTRATION_MESSAGE;
import static pl.tymoteuszborkowski.galleryonline.constants.FrontentMessages.USER_WITH_EMAIL_EXISTS;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid User user, Model model) {

        model.addAttribute("dupa", "dupa");
        Optional<User> userOptional = Optional.ofNullable(userService.findByEmail(user.getEmail()));
        if (!userOptional.isPresent()) {
            userService.saveUser(user);
            return "redirect:/register/successful";
        } else {
            return "redirect:/register/exists/";
        }
    }

    @RequestMapping(value = "/register/successful")
    public String showSuccessfulRegisterMessage(Model model) {
        model.addAttribute(new User());
        model.addAttribute("registrationMessage", SUCCESSFUL_REGISTRATION_MESSAGE);
        return "login";
    }

    @RequestMapping(value = "/register/exists")
    public String showUserExistsMessage(Model model) {
        model.addAttribute(new User());
        model.addAttribute("registrationMessage", USER_WITH_EMAIL_EXISTS);
        return "login";
    }


}
