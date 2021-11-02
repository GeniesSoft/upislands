package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.ContentDto;
import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.model.ContentDtoConverter;

import java.util.List;

public interface ContentService {

    Content saveContent(Content content);

    Content saveContent(String fileName, String path, String contentType);

    List<ContentDto> getContents(List<? extends ContentDtoConverter> list);

    void deleteContent(int contentId);
}
