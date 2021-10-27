package com.geniessoft.backend.utility.customvalidator;

import com.geniessoft.backend.dto.LocationGetDto;
import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.dto.LocationUpdateDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface LocationMapper {
    LocationGetDto locationToLocationGetDto(Location location, Address address);
    Location locationSaveDtoToLocation(LocationSaveDto locationSaveDto);
    Address locationSaveDtoToAddress(LocationSaveDto locationSaveDto);

}
