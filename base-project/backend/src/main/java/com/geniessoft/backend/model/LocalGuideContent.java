package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "LocalGuideContent")
@Getter
@Setter
public class LocalGuideContent implements ContentDtoConverter{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "localGuide_content_id")
    private int localGuideContentId;

    private String contentText;

    @OneToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localGuide_id")
    private LocalGuide localGuide;

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
        return getLocalGuideContentId();
    }
}
