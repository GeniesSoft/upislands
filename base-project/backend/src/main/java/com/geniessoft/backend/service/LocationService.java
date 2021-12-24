package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.ContentDto;
import com.geniessoft.backend.dto.LocationGetFrontendDto;
import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.dto.LocationUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.Location;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface LocationService {

    Location  saveLocation(Location location);

    Location updateLocation(Location location);

    Location findLocationByName(String locationName);

    List<Location> findAllLocations();

    Location findLocationById(int locationId);

    List<Location> findAllLocationsInAState(String state);

    List<ContentDto> getLocationContents(int locationId, int offset, int pageSize);

    void checkLocationName(String locationName);

    void deleteLocation(int locationId);

    void deleteLocationProfileImage(Location location);

    void addLocationProfileImage(int locationId, MultipartFile file);

    void addLocationContent(int locationId, MultipartFile file, String content_text);

    void deleteLocationContent(Integer locationContentId);

    void updateLocationContent(Integer locationContentId, String contentText);

    byte[] getLocationProfileImage(int locationId);

    List<LocationGetFrontendDto> getFrontendDtoList();
}
