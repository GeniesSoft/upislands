package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.GalleryItem;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.repository.LocalGuideContentRepository;
import com.geniessoft.backend.repository.LocationContentRepository;
import com.geniessoft.backend.service.ContentService;
import com.geniessoft.backend.service.LocalGuideContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalGuideContentServiceImpl implements LocalGuideContentService {

    private final ContentService contentService;
    private final LocalGuideContentRepository repository;
    private final FileStoreService fileStoreService;

    @Override
    public LocalGuideContent saveLocalGuideContent(String fileName, String path, String contentType, String contentText, LocalGuide localGuide) {
        Content content = contentService.saveContent(fileName,path,contentType);

        LocalGuideContent localGuideContent = new LocalGuideContent();
        localGuideContent.setContent(content);
        localGuideContent.setLocalGuide(localGuide);
        localGuideContent.setContentText(contentText);

        return repository.save(localGuideContent);
    }

    @Override
    public LocalGuideContent getLocalGuideContent(int localGuideId) {
        return null;
    }

    @Override
    public void deleteLocalGuideContent(Integer localGuideId) {

    }

    @Override
    public void updateLocalGuideContent(Integer localGuideId, String contentText) {

    }

    @Override
    public List<LocationContent> getLocalGuideContentPage(int localGuideId, int offset, int pageSize) {
        return null;
    }

    @Override
    public List<GalleryItem> getLocalGuideContentGallery(int localGuideId, String type) {

        List<LocalGuideContent> localGuideContents = repository.findAllByLocalGuide_LocalGuideId(localGuideId);

        List<GalleryItem> gallery = new LinkedList<>();

        localGuideContents.stream().filter(content -> content.getContentType().contains(type)  ).forEach(content -> {
            GalleryItem galleryItem = new GalleryItem();
            galleryItem.setId(content.getContentId());

            String url = fileStoreService.getPreSignedDownloadUrl(
                    content.getContent().getContentPath(),
                    content.getContent().getContentName());

            galleryItem.setUrl(url);

            gallery.add(galleryItem);
        });

        return gallery;
    }
}
