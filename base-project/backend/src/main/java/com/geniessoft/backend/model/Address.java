package com.geniessoft.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private int streetNumber; //Also known as house/apt no
    private String street;
    private String city;
    private String county;
    private String state;
    private String country;
    private int zip;
}
