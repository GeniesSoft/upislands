package com.geniessoft.backend.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "LocationContent")
@Getter
@Setter
public class LocationContent implements ContentDtoConverter{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_content_id")
    private int locationContentId;

    private String contentText;

    @OneToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Override
    public Date getUploadDate() {
        return content.getUploadDate();
    }

    @Override
    public String getContentType() {
        return content.getContentType();
    }

    @Override
    public String getContentPath() {
        return content.getContentPath();
    }

    @Override
    public String getContentName() {
        return content.getContentName();
    }

    @Override
    public int getContentId() {
        return getLocationContentId();
    }
}
