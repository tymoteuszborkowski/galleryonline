package pl.tymoteuszborkowski.galleryonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.tymoteuszborkowski.galleryonline.model.User;
import pl.tymoteuszborkowski.galleryonline.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

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
    public String registerUser(ModelAndView modelAndView,
                                     @Valid User user) {

        Optional<User> userOptional = Optional.ofNullable(userService.findByEmail(user.getEmail()));
        if (!userOptional.isPresent()) {
            userService.saveUser(user);
        } else {
            modelAndView.addObject("alreadyRegisteredMessage", "Użytkownik o podanym adresie email już istnieje.");
        }

        return "login";
    }

}
