package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;
    private String locationName;
    private String description;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "location")
    private List<CompanyLocation> companyLocationList;

    @OneToMany(mappedBy = "location")
    private List<Review> reviewList;

    @OneToMany(mappedBy = "location")
    private List<LocationContent> LocationContentList;
}
