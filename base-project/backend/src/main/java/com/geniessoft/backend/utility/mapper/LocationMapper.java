package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.LocationGetDto;
import com.geniessoft.backend.dto.LocationGetFrontendDto;
import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.dto.LocationUpdateDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationGetDto locationToLocationGetDto(Location location, Address address);

    @Mapping(source = "location.locationId", target = "id")
    @Mapping(source = "location.locationId", target = "slug")
    @Mapping(source = "location.locationName", target = "title")
    @Mapping(source = "location.description", target = "content")
    LocationGetFrontendDto locationToLocationGetFrontendDto(Location location);

    Location locationSaveDtoToLocation(LocationSaveDto locationSaveDto);

    Location locationUpdateDtoToLocation(LocationUpdateDto locationUpdateDto);
}