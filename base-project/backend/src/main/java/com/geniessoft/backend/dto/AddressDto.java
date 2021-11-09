package com.geniessoft.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDto {

    private String latitude;
    private String longitude;
    private String streetNumber;
    private String street;
    private String city;
    private String county;
    private String state;
    private String country;
    private String zip;

}
