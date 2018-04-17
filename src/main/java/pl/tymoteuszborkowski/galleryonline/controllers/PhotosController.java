package pl.tymoteuszborkowski.galleryonline.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static pl.tymoteuszborkowski.galleryonline.constants.FrontentMessages.UPLOAD_LOGIN_MESSAGE;

@Controller
public class PhotosController {

    @RequestMapping("/access/upload")
    public String accessUploadPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            return "redirect:/upload";
        } else {
            return "redirect:/upload/login";
        }
    }

    @RequestMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }

    @RequestMapping("/upload/login")
    public String showLoginPageBeforeUpload(Model model) {
        model.addAttribute("uploadLoginMessage", UPLOAD_LOGIN_MESSAGE);
        return "login";
    }
}
