package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CompanyContent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_content_id")
    private int companyContentId;

    @OneToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String content_text;
}
