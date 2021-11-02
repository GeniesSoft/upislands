package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class LocalGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int localGuideId;

    private String localGuideName;
    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "local_guide_profile_image_id")
    private Content localGuideProfileImage;
}
