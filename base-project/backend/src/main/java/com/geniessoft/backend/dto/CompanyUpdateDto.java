package com.geniessoft.backend.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class CompanyUpdateDto extends CompanyBaseDto{

    @Positive(message = "You must provide company id.")
    private int companyId;
}
