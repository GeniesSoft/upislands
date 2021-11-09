package com.geniessoft.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JetSkiDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jetSkiDetailsId;

    private Integer totalNumberOfJetSkies;

    private Double sessionPrice;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="jetSkiSession",
            joinColumns = @JoinColumn(name = "jetSkiDetailsId")
    )
    @Column(name = "num_of_used_jetSkies")
    private Map<JetSkiSession, Integer> scheduleMap;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public JetSkiDetails(int totalNumberOfJetSkies, double sessionPrice, Company company) {
        this.totalNumberOfJetSkies = totalNumberOfJetSkies;
        this.sessionPrice = sessionPrice;
        this.company = company;
        this.scheduleMap = new HashMap<>();
    }

    public void update(JetSkiDetails jetSkiDetails) {
        totalNumberOfJetSkies = jetSkiDetails.totalNumberOfJetSkies;
        sessionPrice = jetSkiDetails.sessionPrice;
    }

}
