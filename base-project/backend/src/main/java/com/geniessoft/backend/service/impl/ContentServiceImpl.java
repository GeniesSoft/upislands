package com.geniessoft.backend.service.impl;


import com.geniessoft.backend.dto.ContentDto;
import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.model.ContentDtoConverter;
import com.geniessoft.backend.repository.ContentRepository;
import com.geniessoft.backend.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final FileStoreService fileStoreService;

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

    @Override
    public List<ContentDto> getContents(List<? extends ContentDtoConverter> list){

        return list.stream().map((content)->{

            String path = content.getContent().getContentPath();
            String fileName = content.getContent().getContentName();
            byte[] contentByte = fileStoreService.download(path,fileName);

            ContentDto contentDto = new ContentDto();
            contentDto.setContent(contentByte);
            contentDto.setUploadDate(content.getContent().getUploadDate());
            contentDto.setContentText(content.getContentText());
            contentDto.setContentId(content.getContentId());
            contentDto.setContentType(content.getContent().getContentType());

            return contentDto;

        }).collect(Collectors.toList());
    }
}
