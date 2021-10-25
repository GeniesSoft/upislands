package com.geniessoft.backend.utility.customvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotEmptyOrSpaceOrNullValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyOrSpaceOrNull {

    String message() default "This field cannot be empty.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
