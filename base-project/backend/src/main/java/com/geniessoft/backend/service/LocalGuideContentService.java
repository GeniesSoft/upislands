package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.GalleryItem;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.LocalGuideContent;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.LocationContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocalGuideContentService {

    LocalGuideContent saveLocalGuideContent(
            String fileName,
            String path,
            String contentType,
            String contentText,
            LocalGuide localGuide);

    LocalGuideContent getLocalGuideContent(int localGuideId);

    void deleteLocalGuideContent(Integer localGuideId);

    void updateLocalGuideContent(Integer localGuideId, String contentText);

    List<LocationContent> getLocalGuideContentPage(int localGuideId, int offset, int pageSize);

    List<GalleryItem> getLocalGuideContentGallery(int localGuideId, String type);
}
