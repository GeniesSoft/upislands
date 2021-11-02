package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class CompanyContent implements ContentDtoConverter{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_content_id")
    private int companyContentId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String contentText;

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
        return getCompanyContentId();
    }
}
