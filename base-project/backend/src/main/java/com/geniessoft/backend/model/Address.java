package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class Address {

    private String streetNumber; //Also known as house/apt no
    private String street;
    private String city;
    private String county;
    private String state;
    private String country;
    private String zip;
}
