package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class CompanyRegisterDto {

    @NotEmptyOrSpaceOrNull(message = "Company name cannot be empty.")
    private String companyName;

    @NotEmptyOrSpaceOrNull(message = "Company description cannot be empty.")
    private String companyDescription;

    @Positive
    @NotEmptyOrSpaceOrNull(message = "Job owner cannot be empty.")
    private int userId;
    private String country;
    private String state;
    private String city;
    private String street;
    private String no;
}
