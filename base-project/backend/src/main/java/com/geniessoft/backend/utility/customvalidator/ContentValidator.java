package com.geniessoft.backend.utility.customvalidator;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

import static org.springframework.http.MediaType.*;

public class ContentValidator implements ConstraintValidator<ContentConstraints, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        if(file.isEmpty()){
            throw new EntityNotFoundException("File is empty");
        }

        if(file.getContentType().equals("video/mp4")){
            return true;
        }

        if(!Arrays.asList(
                IMAGE_JPEG_VALUE,
                IMAGE_PNG_VALUE,
                IMAGE_GIF_VALUE).
                contains(file.getContentType())){
            throw new EntityNotFoundException("File is not image.");
        }

        return true;
    }
}
