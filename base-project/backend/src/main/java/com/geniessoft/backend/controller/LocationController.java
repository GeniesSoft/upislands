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
@CrossOrigin(origins = "http://localhost:3000")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper mapper;
    private final AnalysisService analysisService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public String addLocation(@Valid @RequestBody LocationSaveDto locationDto){
      locationService.saveLocation(locationDto);
      return "Location successfully saved";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public LocationGetDto getLocationById(@PathVariable(value = "id") Integer id){
        Location location = locationService.findLocationById(id);
        Address address = location.getAddress();
        return mapper.locationToLocationGetDto(location, address);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public String updateLocation(@Valid @RequestBody LocationUpdateDto locationUpdateDto){
        locationService.updateLocation(locationUpdateDto);
        return "Location successfully updated";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public String deleteLocation(@PathVariable(value = "id") Integer id){
       locationService.deleteLocation(id);
        return "Location is deleted";
    }
    @GetMapping(value = "/mostBooked")
    public ResponseEntity<LocationGetDto> getMostBookedLocation(){
        Location location = analysisService.findMostBookedLocation();
        Address address= location.getAddress();
        LocationGetDto locationGetDto= mapper.locationToLocationGetDto(location, address);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationGetDto);
    }

    @GetMapping(value = "/bookedLocations")
    public ResponseEntity<List<LocationGetDto>> getBookedLocationsByOrder(){
         List<Location> locations = analysisService.findBookedLocationsByBookingDescOrder();
         List<LocationGetDto> locationGetDtoList = new ArrayList<>();
        for (Location location: locations) {
            Address address = location.getAddress();
            LocationGetDto locationGetDto = mapper.locationToLocationGetDto(location, address);
            locationGetDtoList.add(locationGetDto);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationGetDtoList);
    }
    @GetMapping(value = "/LocationsAverage")
    public ResponseEntity<Map<LocationGetDto,Double>> getLocationsByAverageOrder(){
        Map<Location,Double> locationAverageMap = analysisService.findLocationsByRatingDescOrder();
        Map<LocationGetDto,Double> locationDtoAverageMap = new HashMap<>();
        for (Map.Entry<Location,Double> entry : locationAverageMap.entrySet() ){
            Location location = entry.getKey();
            LocationGetDto  locationGetDto = mapper.locationToLocationGetDto(location, location.getAddress());
            locationDtoAverageMap.put(locationGetDto,entry.getValue());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationDtoAverageMap);
    }

    @PostMapping(
            path = "{locationId}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> addLocationProfileImage(
            @PathVariable("locationId") int locationId,
            @RequestParam("file") @ContentConstraints MultipartFile file){
        locationService.addLocationProfileImage(locationId ,file);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Location profile image is uploaded.");
    }

    @PostMapping(
            path = "{locationId}/content",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> addLocationContent(
            @PathVariable("locationId") int locationId,
            @RequestParam("file") @ContentConstraints MultipartFile file,
            @RequestParam("contentText") String content_text){
        locationService.addLocationContent(locationId ,file, content_text);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Location content is uploaded.");
    }

    @DeleteMapping(value = "/content")
    public ResponseEntity<String> deleteLocationContent
            (@RequestParam(value = "locationContentId") Integer locationContentId){
        locationService.deleteLocationContent(locationContentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Location content is successfully deleted.");
    }

    @PutMapping(value = "/content")
    public ResponseEntity<String> updateLocationContent
            (@RequestParam(value = "locationContentId") Integer locationContentId,
             @RequestParam(value = "contentText") String contentText){
        locationService.updateLocationContent(locationContentId,contentText);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Location content is successfully updated.");
    }

    @GetMapping(value = "/contents/{locationId}/{offset}/{pageSize}")
    public ResponseEntity<List<ContentDto>> getLocationContentList(
            @PathVariable("locationId") int locationId,
            @PathVariable("offset") int offset,
            @PathVariable("pageSize") int pageSize){

        List<ContentDto> contentDtoList = locationService.
                getLocationContents(locationId, offset, pageSize);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contentDtoList);
    }

    @GetMapping(value = "/{locationId}/profileImage")
    public ResponseEntity<ProfileImageDto> getProfileImage(
            @PathVariable("locationId") int locationId){

        byte[] image = locationService.getLocationProfileImage(locationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ProfileImageDto(image));
    }
}
