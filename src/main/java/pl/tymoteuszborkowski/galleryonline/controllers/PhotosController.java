package pl.tymoteuszborkowski.galleryonline.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tymoteuszborkowski.galleryonline.model.User;

import static pl.tymoteuszborkowski.galleryonline.constants.FrontentMessages.UPLOAD_LOGIN_MESSAGE;

@Controller
public class PhotosController {

    @RequestMapping("/access/upload")
    public String accessUploadPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/upload/login";
        } else {
            return "redirect:/upload";
        }
    }

    @RequestMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @RequestMapping("/upload/login")
    public String showLoginPageBeforeUpload(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("uploadLoginMessage", UPLOAD_LOGIN_MESSAGE);
        return "login";
    }
}
