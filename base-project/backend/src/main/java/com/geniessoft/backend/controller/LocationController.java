package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.LocationGetDto;
import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.dto.LocationUpdateDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.service.LocationService;
import com.geniessoft.backend.utility.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper mapper;


     @PostMapping(value = "/save")
    public ResponseEntity<String> addLocation(@Valid @RequestBody LocationSaveDto locationDto){
      locationService.saveLocation(locationDto);
      return ResponseEntity
                .status(HttpStatus.OK)
                .body("Location is saved.");
    }

    @PutMapping
    public ResponseEntity<String> updateLocation(@Valid @RequestBody LocationUpdateDto locationUpdateDto){
        locationService.updateLocation(locationUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Location is updated");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLocation(@RequestParam(value = "locationId") Integer locationId){
       locationService.deleteLocation(locationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Location is deleted");
    }

    @GetMapping
    public ResponseEntity<LocationGetDto> getLocationById(@RequestParam(value = "locationId") Integer locationId){

        Location location = locationService.findLocationById(locationId);
        Address address = location.getAddress();
        LocationGetDto dto = mapper.locationToLocationGetDto(location, address);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);


}
}
