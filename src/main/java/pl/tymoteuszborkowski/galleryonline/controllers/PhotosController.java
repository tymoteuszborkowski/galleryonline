package pl.tymoteuszborkowski.galleryonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.tymoteuszborkowski.galleryonline.config.spring.UserPrincipal;
import pl.tymoteuszborkowski.galleryonline.model.Photo;
import pl.tymoteuszborkowski.galleryonline.model.User;
import pl.tymoteuszborkowski.galleryonline.repositories.PhotoRepository;
import pl.tymoteuszborkowski.galleryonline.utils.PhotoUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static pl.tymoteuszborkowski.galleryonline.constants.FrontentMessages.*;

@Controller
public class PhotosController {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotosController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

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

    @RequestMapping("/perform_upload")
    public String performUploadPhoto(@RequestParam("photo") MultipartFile incomingPhoto, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        long loggedUserId = userPrincipal.getUser().getId();
        String generatedPhotoPath = PhotoUtils.generatePhotoPath(incomingPhoto.getOriginalFilename());

        if (!PhotoUtils.isProperPhotoExtension(incomingPhoto.getOriginalFilename())) {
            model.addAttribute("uploadMessage", INCORRECT_EXTENSION);
            return "upload";
        }

        try {
            File newPhotoFIle = new File(generatedPhotoPath);
            boolean isProperlyCreated = newPhotoFIle.createNewFile();
            if (isProperlyCreated) {
                Files.copy(incomingPhoto.getInputStream(), Paths.get(generatedPhotoPath), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("uploadMessage", ERROR_DURING_UPLOAD);
            return "upload";
        }

        Photo uploadedPhoto = new Photo(loggedUserId, "/"+generatedPhotoPath);
        photoRepository.save(uploadedPhoto);
        model.addAttribute("uploadMessage", CORRECT_UPLOAD);

        return "upload";
    }

    @ResponseBody
    @RequestMapping(value = "/stored_photos/{photoName}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] producePhotoBytes(@PathVariable String photoName) throws IOException {
        Path pathToPhoto = Paths.get(PhotoUtils.STORED_PHOTOS_PATH + photoName);
        return Files.readAllBytes(pathToPhoto);
    }

}
