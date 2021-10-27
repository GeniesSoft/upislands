package com.geniessoft.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    private String companyName;
    private String companyDescription;
    private boolean deleted = false;

    @OneToOne
    @JoinColumn(name = "company_address_id")
    private Address companyAddress;

    @OneToOne
    @JoinColumn(name = "job_owner_id")
    private User jobOwner;

    /*@OneToMany(mappedBy = "company", orphanRemoval = true)
    private List<JetSki> jetSkiList;

    @OneToMany(mappedBy = "company")
    private List<CompanyLocation> companyLocationList;*/
}
