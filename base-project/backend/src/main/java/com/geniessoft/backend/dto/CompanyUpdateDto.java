package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.LocationListConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class CompanyUpdateDto extends CompanyBaseDto{

    @Positive(message = "You must provide company id.")
    private int companyId;

    @LocationListConstraint(message = "You must add at least one location.")
    private List<Integer> locationIdList;

}
