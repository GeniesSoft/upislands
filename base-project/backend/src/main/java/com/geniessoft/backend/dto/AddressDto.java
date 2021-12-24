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

    private int id;
    private double lat;
    private double lng;

    private String formattedAddress;
    private String city;
    private String zipcode;

    public static final AddressDto DEFAULT_ADDRESS;

    static {
        DEFAULT_ADDRESS = new AddressDto(
                0,
                60.70d,
                -76.6d,
                "Default Address",
                "Default City",
                "Default Zipcode"
        );
    }

}
