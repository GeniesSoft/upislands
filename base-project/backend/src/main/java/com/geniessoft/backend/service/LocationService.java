package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.dto.LocationUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.Location;
import org.springframework.web.multipart.MultipartFile;


public interface LocationService {

    Location  saveLocation(LocationSaveDto location);

    Location updateLocation(LocationUpdateDto locationUpdateDto);

    Location findLocationByName(String locationName);

    Location findLocationById(int locationId);

    void checkLocationName(String locationName);

    void deleteLocation(int locationId);

    void deleteLocationProfileImage(Location location);

    void addLocationProfileImage(int locationId, MultipartFile file);

    void addLocationContent(int locationId, MultipartFile file, String content_text);

}
