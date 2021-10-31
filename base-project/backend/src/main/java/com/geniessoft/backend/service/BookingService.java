package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.BookingBaseDto;
import com.geniessoft.backend.dto.BookingUpdateDto;
import com.geniessoft.backend.model.Booking;

public interface BookingService {

    Booking findBookingById(int bookingId);
    Booking saveBooking(BookingBaseDto bookingSaveDto);
    Booking updateBooking(BookingUpdateDto bookingUpdateDto);
    void deleteBooking(int bookingId);

}
