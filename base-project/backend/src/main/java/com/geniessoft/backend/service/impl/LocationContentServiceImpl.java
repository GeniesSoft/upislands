package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.CompanyContent;
import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.LocationContent;
import com.geniessoft.backend.repository.LocationContentRepository;
import com.geniessoft.backend.service.ContentService;
import com.geniessoft.backend.service.LocationContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationContentServiceImpl implements LocationContentService {

    private final ContentService contentService;
    private final LocationContentRepository locationContentRepository;
    private final FileStoreService fileStoreService;

    @Override
    public LocationContent saveLocationContent(
            String fileName,
            String path,
            String contentType,
            String contentText,
            Location location) {

        Content content = contentService.saveContent(fileName,path,contentType);

        LocationContent locationContent = new LocationContent();
        locationContent.setContent(content);
        locationContent.setLocation(location);
        locationContent.setContentText(contentText);

        return locationContentRepository.save(locationContent);
    }

    @Override
    public void deleteLocationContent(Integer locationContentId) {
        LocationContent locationContent = getLocationContent(locationContentId);
        locationContentRepository.deleteById(locationContentId);
        fileStoreService.delete(
                locationContent.getContent().getContentPath(),
                locationContent.getContent().getContentName());
    }

    @Override
    public LocationContent getLocationContent(int locationId) {
        Optional<LocationContent> locationContent = locationContentRepository.findById(locationId);
        if(locationContent.isEmpty()){
            throw new EntityNotFoundException("Location content is not found.");
        }
        return locationContent.get();
    }

    @Override
    public void updateLocationContent(Integer locationContentId, String contentText) {
        LocationContent locationContent = getLocationContent(locationContentId);
        locationContent.setContentText(contentText);
        locationContentRepository.save(locationContent);
    }

    @Override
    public List<LocationContent> getLocationContentPage(int locationId, int offset, int pageSize) {

        Page<LocationContent> locationContentPage =
                locationContentRepository.findAll(PageRequest.of(offset,pageSize));

        return locationContentPage.toList();
    }
}
