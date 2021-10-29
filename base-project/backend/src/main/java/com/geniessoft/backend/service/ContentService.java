package com.geniessoft.backend.service;

import com.geniessoft.backend.model.Content;

public interface ContentService {

    Content saveContent(Content content);

    Content saveContent(String fileName, String path, String contentType);

    void deleteContent(int contentId);
}
