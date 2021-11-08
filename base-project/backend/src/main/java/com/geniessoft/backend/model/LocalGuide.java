package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
public class LocalGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int localGuideId;

    private String localGuideName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="localGuideSession",
            joinColumns = @JoinColumn(name = "localGuideId")
    )
    @Column(name = "is_local_guide_available")
    private Map<LocalGuideSession,Boolean> scheduleMap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "local_guide_profile_image_id")
    private Content localGuideProfileImage;
}
