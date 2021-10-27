package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CompanyBaseDto {

    @NotEmptyOrSpaceOrNull(message = "Company name cannot be empty.")
    protected String companyName;

    @NotEmptyOrSpaceOrNull(message = "Company description cannot be empty.")
    protected String companyDescription;
    protected String country;
    protected String state;
    protected String city;
    protected String street;
    protected String no;
}
