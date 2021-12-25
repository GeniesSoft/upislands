package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.BookingBaseDto;
import com.geniessoft.backend.dto.BookingGetDto;
import com.geniessoft.backend.dto.BookingUpdateDto;
import com.geniessoft.backend.model.Booking;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking bookingDtoToBooking(BookingBaseDto bookingDto);
    BookingBaseDto bookingToBookingBaseDto(Booking booking, Location location, Company Company, User user);
    BookingBaseDto bookingUpdateDtoToBookingBaseDto(BookingUpdateDto bookingUpdateDto);

    @Mapping(source = "booking.bookingLocation.locationId", target = "locationId")
    @Mapping(source = "booking.bookingCompany.companyId", target = "companyId")
    @Mapping(source = "booking.localGuide.localGuideId", target = "localGuideId")
    @Mapping(source = "booking.user.userId", target = "userId")
    BookingGetDto bookingToBookingGetDto(Booking booking);

}
