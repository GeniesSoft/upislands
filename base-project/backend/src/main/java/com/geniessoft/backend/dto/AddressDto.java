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
    public static final AddressDto Venetion_Island;
    public static final AddressDto Star_Island;
    public static final AddressDto Miami_Marine_Stadium;

    static {
        DEFAULT_ADDRESS = new AddressDto(
                0,
                60.70d,
                -76.6d,
                "Default Address",
                "Default City",
                "Default Zipcode"
        );
        Venetion_Island = new AddressDto(
                1,
                25.790225890688255d,
                -80.156786273679d,
                "Miami Beach, Florida 33139, USA",
                "Florida",
                "Zipcode"
        );
        Star_Island = new AddressDto(
                2,
                25.77711395334303d,
                -80.1505102426341d,
                "Miami Beach, Florida 33139, USA",
                "Florida",
                "Zipcode"
        );
        Miami_Marine_Stadium = new AddressDto(
                3,
                25.742661777829827d,
                -80.17085258465686d,
                "3501 Rickenbacker Causeway, Miami, FL 33149, USA",
                "Florida",
                "Zipcode"
        );
    }

}
