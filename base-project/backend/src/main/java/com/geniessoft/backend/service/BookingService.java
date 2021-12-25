package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.BookingBaseDto;
import com.geniessoft.backend.dto.BookingUpdateDto;
import com.geniessoft.backend.model.Booking;

import java.util.List;

public interface BookingService {

    Booking findBookingById(int bookingId);
    Booking saveBooking(BookingBaseDto bookingSaveDto);
    void updateBooking(BookingUpdateDto bookingUpdateDto);
    List<Booking> findAllBookings();
    List<Booking> findAllBookingsByLocationOrder();
    List<Booking> findAllBookingsByLocalGuideOrder();
    List<Booking> findAllBookingsByUserOrder();
    void deleteBooking(int bookingId);

}
