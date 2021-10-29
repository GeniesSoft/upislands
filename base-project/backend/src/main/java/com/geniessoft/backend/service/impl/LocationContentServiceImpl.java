package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.LocationContent;
import com.geniessoft.backend.repository.LocationContentRepository;
import com.geniessoft.backend.service.ContentService;
import com.geniessoft.backend.service.LocationContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationContentServiceImpl implements LocationContentService {

    private final ContentService contentService;
    private final LocationContentRepository locationContentRepository;

    @Override
    public LocationContent saveLocationContent(String fileName, String path, String contentType, String contentText, Location location) {

        Content content = contentService.saveContent(fileName,path,contentType);

        LocationContent locationContent = new LocationContent();
        locationContent.setContent(content);
        locationContent.setLocation(location);
        locationContent.setContent_text(contentText);

        return locationContentRepository.save(locationContent);
    }
}
