package pl.tymoteuszborkowski.galleryonline.utils;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PhotoUtils {

    public static final String STORED_PHOTOS_PATH = "stored_photos/";
    private static final List<String> PHOTOS_EXTENSIONS = new ArrayList<>(Arrays.asList(ImageIO.getReaderFormatNames()));

    public static boolean isProperPhotoExtension(String filename) {
        String incomingExtension = FilenameUtils.getExtension(filename);
        return PHOTOS_EXTENSIONS.contains(incomingExtension);
    }

    public static String generatePhotoPath(String originalName) {
        return STORED_PHOTOS_PATH + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalName);
    }


}
