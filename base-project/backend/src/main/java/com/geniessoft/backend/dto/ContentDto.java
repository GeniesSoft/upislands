package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ContentDto {

    private int contentId;
    private String contentText;
    private String contentType;
    private Date uploadDate;
    private byte[] content;

    @Override
    public String toString() {
        return "ContentDto{" +
                "contentId=" + contentId +
                ", contentText='" + contentText + '\'' +
                ", contentType='" + contentType + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
