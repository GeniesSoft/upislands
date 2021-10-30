package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;
    private boolean deleted = false;

    @Column(unique = true)
    private String locationName;
    private String tripTime;
    private boolean needExperience;
    private String description;

    @OneToOne
    private Address address;

    @ManyToMany(mappedBy = "locationList")
    private List<Company> companyList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "location_profile_image_id")
    private Content locationProfileImage;
}
