package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LocationSaveDto extends LocationBaseDto{

    @NotEmptyOrSpaceOrNull(message = "Location Name cannot be empty.")
    protected String locationName;

    protected int streetNumber; //Also known as house/apt no
    protected int zip;

    @NotEmptyOrSpaceOrNull(message = "Address cannot be empty.")
    protected String street;
    protected String city;
    protected String county;
    protected String state;
    protected String country;

}
