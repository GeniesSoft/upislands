package com.geniessoft.backend.dto;


import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public abstract class LocationDtoBase {

    @NotEmptyOrSpaceOrNull(message = "Location Name cannot be empty.")
    String locationName;
    @NotEmptyOrSpaceOrNull(message = "Description cannot be empty.")
    String description;
    @NotEmptyOrSpaceOrNull(message = "Address cannot be empty.")
    Address address;

}
