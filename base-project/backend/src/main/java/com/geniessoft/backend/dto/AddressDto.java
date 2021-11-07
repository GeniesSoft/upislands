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

    private int streetNumber;
    private String street;
    private String city;
    private String county;
    private String state;
    private String country;
    private int zip;

}
