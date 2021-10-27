package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationGetDto extends LocationBaseDto{

    //private int locationId; //location id is not accessible for now.
    private String locationName;
    private int streetNumber; //Also known as house/apt no
    private String street;
    private String city;
    private String county;
    private String state;
    private String country;
    private int zip;
}
