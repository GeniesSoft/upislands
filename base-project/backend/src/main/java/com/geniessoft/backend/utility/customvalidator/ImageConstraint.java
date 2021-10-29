package com.geniessoft.backend.utility.customvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Constraint(validatedBy = ImageValidator.class)
@Target({  METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageConstraint {

    String message() default "This file is not image";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
