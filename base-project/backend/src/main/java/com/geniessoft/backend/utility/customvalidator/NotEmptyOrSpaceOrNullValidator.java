package com.geniessoft.backend.utility.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyOrSpaceOrNullValidator implements ConstraintValidator<NotEmptyOrSpaceOrNull, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty() && !value.trim().isEmpty();
    }
}
