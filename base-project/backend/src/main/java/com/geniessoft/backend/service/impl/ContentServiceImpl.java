package com.geniessoft.backend.service.impl;


import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.repository.ContentRepository;
import com.geniessoft.backend.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Override
    public Content saveContent(Content content) {
        Content savedContent = contentRepository.save(content);
        return savedContent;
    }

    @Override
    public void deleteContent(int contentId) {
        contentRepository.deleteById(contentId);
    }

    @Override
    public Content saveContent(String fileName, String path, String contentType) {

        Content content = new Content();
        content.setContentName(fileName);
        content.setContentPath(path);
        content.setContentType(contentType);
        content.setUploadDate(new Date());
        return saveContent(content);
    }
}
