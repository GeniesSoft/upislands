package com.geniessoft.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentId;
    private String contentName;
    private Date uploadDate;
    private ContentType contentType;
    private String contentPath;
}
