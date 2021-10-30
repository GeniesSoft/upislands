package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.BookingBaseDto;
import com.geniessoft.backend.dto.BookingUpdateDto;
import com.geniessoft.backend.model.Booking;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking bookingDtoToBooking(BookingBaseDto bookingDto);
    BookingBaseDto bookingToBookingBaseDto(Booking booking, Location location, Company Company, User user);
    BookingBaseDto bookingUpdateDtoToBookingBaseDto(BookingUpdateDto bookingUpdateDto);

}
