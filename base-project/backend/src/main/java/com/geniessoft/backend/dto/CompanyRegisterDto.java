package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.LocationListConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class CompanyRegisterDto extends CompanyBaseDto{

    @Positive(message = "You must enter job owner")
    private int userId;

    @LocationListConstraint(message = "You must add at least one location.")
    private List<Integer> locationIdList;
}
