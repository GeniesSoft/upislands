package com.geniessoft.backend.model;

import java.util.Date;

public interface ContentDtoConverter {

    Date getUploadDate();

    String getContentText();

    int getContentId();

    String getContentType();

    String getContentPath();

    String getContentName();

    Content getContent();
}
