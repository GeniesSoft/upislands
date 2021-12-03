package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.*;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.service.AnalysisService;
import com.geniessoft.backend.service.LocationService;
import com.geniessoft.backend.utility.customvalidator.ContentConstraints;
import com.geniessoft.backend.utility.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/locations")
@RequiredArgsConstructor
@Validated
public class LocationController {

    private final LocationService locationService;
    private final AnalysisService analysisService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String addLocation(@Valid @RequestBody LocationSaveDto locationDto){
        locationService.saveLocation(
                LocationMapper.INSTANCE.locationSaveDtoToLocation(locationDto)
        );
        return "Location successfully saved";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public LocationGetDto getLocationById(@PathVariable(value = "id") Integer id){
        Location location = locationService.findLocationById(id);
        Address address = location.getAddress();
        return LocationMapper.INSTANCE.locationToLocationGetDto(location, address);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String updateLocation(@Valid @RequestBody LocationUpdateDto locationUpdateDto){
        locationService.updateLocation(
                LocationMapper.INSTANCE.locationUpdateDtoToLocation(locationUpdateDto)
        );
        return "Location successfully updated";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String deleteLocation(@PathVariable(value = "id") Integer id){
       locationService.deleteLocation(id);
        return "Location is deleted";
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/mostBooked")
    public LocationGetDto getMostBookedLocation(){
        Location location = analysisService.findMostBookedLocation();
        Address address= location.getAddress();
        LocationGetDto locationGetDto= LocationMapper.INSTANCE.locationToLocationGetDto(location, address);
        return locationGetDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bookedLocations")
    public List<LocationGetDto> getBookedLocationsByOrder(){
         List<Location> locations = analysisService.findBookedLocationsByBookingDescOrder();
         List<LocationGetDto> locationGetDtoList = new ArrayList<>();
        for (Location location: locations) {
            Address address = location.getAddress();
            LocationGetDto locationGetDto = LocationMapper.INSTANCE.locationToLocationGetDto(location, address);
            locationGetDtoList.add(locationGetDto);
        }
        return locationGetDtoList;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/LocationsAverage")
    public Map<Integer,Double> getLocationsByAverageOrder(){
        Map<Location,Double> locationAverageMap = analysisService.findLocationsByRatingDescOrder();
        Map<Integer,Double> locationIdAverageMap = new HashMap<>();
        for (Map.Entry<Location,Double> entry : locationAverageMap.entrySet() ){
            Location location = entry.getKey();
            locationIdAverageMap.put(location.getLocationId(),entry.getValue());
        }
        return locationIdAverageMap;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/state-locations")
    public List<LocationGetDto> getLocationsByState(@Valid @RequestParam(value = "state") String state){
        List<Location> locations =  locationService.findAllLocationsInAState(state);
        List<LocationGetDto> locationGetDtoList = new ArrayList<>();
        for (Location location: locations) {
            locationGetDtoList.add(LocationMapper.INSTANCE.locationToLocationGetDto(location, location.getAddress()));
        }
        return locationGetDtoList;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(
            path = "/{locationId}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String addLocationProfileImage(
            @PathVariable("locationId") int locationId,
            @RequestParam("file") @ContentConstraints MultipartFile file){
        locationService.addLocationProfileImage(locationId ,file);
        return "Location profile image is uploaded.";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(
            path = "/{locationId}/content",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String addLocationContent(
            @PathVariable("locationId") int locationId,
            @RequestParam("file") @ContentConstraints MultipartFile file,
            @RequestParam("contentText") String content_text){
        locationService.addLocationContent(locationId ,file, content_text);
        return "Location content is uploaded.";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/content")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String deleteLocationContent
            (@RequestParam(value = "locationContentId") Integer locationContentId){
        locationService.deleteLocationContent(locationContentId);
        return "Location content is successfully deleted.";
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/content")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String updateLocationContent
            (@RequestParam(value = "locationContentId") Integer locationContentId,
             @RequestParam(value = "contentText") String contentText){
        locationService.updateLocationContent(locationContentId,contentText);
        return "Location content is successfully updated.";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/contents/{locationId}/{offset}/{pageSize}")
    public List<ContentDto> getLocationContentList(
            @PathVariable("locationId") int locationId,
            @PathVariable("offset") int offset,
            @PathVariable("pageSize") int pageSize){
        List<ContentDto> contentDtoList = locationService.
                getLocationContents(locationId, offset, pageSize);

        return contentDtoList;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{locationId}/profileImage")
    public ProfileImageDto getProfileImage(
            @PathVariable("locationId") int locationId){

        byte[] image = locationService.getLocationProfileImage(locationId);
        return new ProfileImageDto(image);
    }
}
