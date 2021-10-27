package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.LocationBaseDto;
import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.model.Location;


public interface LocationService {

    Location  saveLocation(LocationSaveDto location);
    Location updateLocation(LocationBaseDto locationDto);
    void checkLocationName(String locationName);
    void deleteLocation(int locationId);
    Location findLocationByName(String locationName);
    Location findLocationById(int locationId);

}
