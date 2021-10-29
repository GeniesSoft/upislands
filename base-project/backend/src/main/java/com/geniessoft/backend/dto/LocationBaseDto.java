package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class LocationBaseDto {

    @NotEmptyOrSpaceOrNull(message = "Description cannot be empty.")
    protected String description;


}

