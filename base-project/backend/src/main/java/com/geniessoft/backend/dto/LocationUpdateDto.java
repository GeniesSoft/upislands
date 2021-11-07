package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class LocationUpdateDto extends LocationBaseDto {

    @Positive(message = "Location Id must provide")
    private int locationId;

}
