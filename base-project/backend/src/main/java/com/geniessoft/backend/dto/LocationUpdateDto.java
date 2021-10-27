package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class LocationUpdateDto extends LocationBaseDto {

    @Positive(message = "You must provide Location ID.")
    private int locationId;

}
