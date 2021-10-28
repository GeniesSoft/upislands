package com.geniessoft.backend.service.impl;


import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.repository.ContentRepository;
import com.geniessoft.backend.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Override
    public Content saveContent(Content content) {
        Content savedContent = contentRepository.save(content);
        return savedContent;
    }
}
