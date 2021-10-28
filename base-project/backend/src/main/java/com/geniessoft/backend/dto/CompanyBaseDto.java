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
    private int streetNumber; //Also known as house/apt no
    private String street;
    private String city;
    private String county;
    private String state;
    private String country;
    private int zip;
}
