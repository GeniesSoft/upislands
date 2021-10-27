package com.geniessoft.backend.dto;


import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;


@Getter
@Setter
public abstract class LocationDtoBase {

    @NotEmptyOrSpaceOrNull(message = "Location Name cannot be empty.")
    protected String locationName;

    @NotEmptyOrSpaceOrNull(message = "Description cannot be empty.")
    protected String description;

    @Positive
    @NotEmptyOrSpaceOrNull(message = "Address cannot be empty.")
    protected int streetNumber; //Also known as house/apt no
    protected String street;
    protected String city;
    protected String county;
    protected String state;
    protected String country;
    protected int zip;
}

