package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.ContentDto;
import com.geniessoft.backend.dto.LocationGetDto;
import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.dto.LocationUpdateDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.LocationContent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel="spring")
public interface LocationMapper {

    LocationGetDto locationToLocationGetDto(Location location, Address address);

    Location locationSaveDtoToLocation(LocationSaveDto locationSaveDto);

    Address locationSaveDtoToAddress(LocationSaveDto locationSaveDto);

    void updateLocation(@MappingTarget Location location, LocationUpdateDto locationUpdateDto);
}
