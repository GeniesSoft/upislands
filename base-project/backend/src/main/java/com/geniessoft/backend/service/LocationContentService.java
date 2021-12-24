package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.GalleryItem;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.CompanyContent;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.LocationContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationContentService {

    LocationContent saveLocationContent(
            String fileName,
            String path,
            String contentType,
            String contentText,
            Location location);

    LocationContent getLocationContent(int locationId);

    void deleteLocationContent(Integer locationContentId);

    void updateLocationContent(Integer locationContentId, String contentText);

    List<LocationContent> getLocationContentPage(int locationId, int offset, int pageSize);

    List<GalleryItem> getLocationGallery(int locationId, String type);

}
