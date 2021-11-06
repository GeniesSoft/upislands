package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.ContentDto;
import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.dto.LocationUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.Location;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface LocationService {

    Location  saveLocation(LocationSaveDto location);

    Location updateLocation(LocationUpdateDto locationUpdateDto);

    Location findLocationByName(String locationName);

    List<Location> findAllLocations();

    Location findLocationById(int locationId);

    List<ContentDto> getLocationContents(int locationId, int offset, int pageSize);

    void checkLocationName(String locationName);

    void deleteLocation(int locationId);

    void deleteLocationProfileImage(Location location);

    void addLocationProfileImage(int locationId, MultipartFile file);

    void addLocationContent(int locationId, MultipartFile file, String content_text);

    void deleteLocationContent(Integer locationContentId);

    void updateLocationContent(Integer locationContentId, String contentText);

    byte[] getLocationProfileImage(int locationId);
}
