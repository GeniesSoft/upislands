package com.geniessoft.backend.utility.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class LocationListValidator implements ConstraintValidator<LocationListConstraint, List<Integer>> {

    @Override
    public boolean isValid(List<Integer> value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        }
        else if(value.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
}
