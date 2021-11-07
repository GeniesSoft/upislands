package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class LocationBaseDto {

    @NotEmptyOrSpaceOrNull(message = "Location Name cannot be empty")
    protected String locationName;

    protected String description;

    protected String tripTime;
    protected boolean needExperience;

    protected AddressDto address;

}

