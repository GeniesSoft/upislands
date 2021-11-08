package com.geniessoft.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @Column(unique = true)
    private String companyName;
    private String companyDescription;
    private boolean deleted = false;

    @Embedded
    private Address address;

    @OneToOne
    @JoinColumn(name = "company_profile_image_id")
    private Content companyProfileImage;

    @OneToOne
    @JoinColumn(name = "job_owner_id")
    private User jobOwner;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "company_locations",
            joinColumns = { @JoinColumn(name = "company_id") },
            inverseJoinColumns = { @JoinColumn(name = "location_id") }
    )
    private List<Location> locationList = new ArrayList<>();

}
