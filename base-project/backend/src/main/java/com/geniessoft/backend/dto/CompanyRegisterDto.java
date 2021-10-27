package com.geniessoft.backend.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class CompanyRegisterDto extends CompanyBaseDto{

    @Positive(message = "You must enter job owner")
    private int userId;
}
