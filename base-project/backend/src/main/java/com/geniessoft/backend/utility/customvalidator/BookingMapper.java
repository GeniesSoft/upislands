package com.geniessoft.backend.utility.customvalidator;

import com.geniessoft.backend.dto.BookingBaseDto;
import com.geniessoft.backend.model.Booking;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking bookingDtoToBooking(BookingBaseDto bookingDto);

}
