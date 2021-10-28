package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.LocationBaseDto;
import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.dto.LocationUpdateDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.repository.LocationRepository;
import com.geniessoft.backend.service.AddressService;
import com.geniessoft.backend.service.LocationService;
import com.geniessoft.backend.utility.customvalidator.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper mapper;
    private final AddressService addressService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Location saveLocation(LocationSaveDto locationSaveDto) {
        checkLocationName(locationSaveDto.getLocationName());
        Location location = mapper.locationSaveDtoToLocation(locationSaveDto);
        Address address = mapper.locationSaveDtoToAddress(locationSaveDto);
        addressService.saveAddress(address);
        location.setAddress(address);
        return locationRepository.save(location);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public Location updateLocation(LocationUpdateDto locationUpdateDto){
        Location location = findLocationById(locationUpdateDto.getLocationId());
        //checkLocationName(location.getLocationName());
        mapper.updateLocation(location, locationUpdateDto);
        locationRepository.save(location);
        return location;
    }

    @Override
    public void checkLocationName(String locationName) {
        Optional<Location> location = locationRepository.findFirstByLocationNameEquals(locationName.trim());

        if (location.isPresent()){
            if (location.get().isDeleted()){
                throw new EntityExistsException("This Location is deleted.");
            }
            else
                throw new EntityExistsException("This location already exists.");
        }
    }

    @Override
    public void deleteLocation(int locationId) {
        Location location = findLocationById(locationId);
        location.setDeleted(true);
        locationRepository.save(location);
    }

    @Override
    public Location findLocationByName(String locationName) {
        return null;
    }

    @Override
    public Location findLocationById(int locationId) {
        Optional<Location> location = locationRepository
                .findLocationByLocationIdAndDeletedIsFalse(locationId);
        return location.orElseThrow(() -> new EntityNotFoundException("Location is not found."));

    }
}
