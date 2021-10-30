package com.geniessoft.backend.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "LocationContent")
@Getter
@Setter
public class LocationContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_content_id")
    private int locationContentId;

    private String content_text;

    @OneToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}
