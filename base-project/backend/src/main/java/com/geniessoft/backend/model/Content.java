package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int contentId;
    protected String contentName;
    protected Date uploadDate;
    protected String contentType;
    protected String contentPath;
    protected String contentUrl;
}
