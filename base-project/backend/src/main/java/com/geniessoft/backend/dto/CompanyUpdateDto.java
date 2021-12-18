package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.LocationListConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class CompanyUpdateDto extends CompanyBaseDto{

    @Positive(message = "Company Id must provide")
    private int companyId;

    @Positive(message = "User Id must provide")
    private int userId;

}
