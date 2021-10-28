package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.BookingBaseDto;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.repository.BookingRepository;
import com.geniessoft.backend.service.BookingService;
import com.geniessoft.backend.service.CompanyService;
import com.geniessoft.backend.service.LocationService;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

   private final BookingRepository bookingRepository;
   private final BookingMapper mapper;
   private final UserService userService;
   private final LocationService locationService;
   private final CompanyService companyService;
    //private final JetSkiService jetSkiService;

    @Override
    public Booking findBookingById(int bookingId) {
        Optional<Booking> booking = bookingRepository.findBookingByBookingIdAndActiveIsTrue(bookingId);
        return booking.orElseThrow(() -> new EntityNotFoundException("Booking is not found."));
    }

    @Override
    public Booking saveBooking(BookingBaseDto bookingSaveDto) {

        User user = userService.findUser(bookingSaveDto.getUserId());
        Location location = locationService.findLocationById(bookingSaveDto.getLocationId());
        Booking booking = mapper.bookingDtoToBooking(bookingSaveDto);
        Company company = companyService.findCompanyById(bookingSaveDto.getCompanyId());
        booking.setUser(user);
        booking.setBookingLocation(location);
        booking.setBookingCompany(company);
        //jetSkiService.updateCount(bookingSaveDto.getCompanyId(),bookingSaveDto.getJetSkiCount());
        bookingRepository.save(booking);

        return booking;
    }


    @Override
    public Booking updateBooking(BookingBaseDto bookingUpdateDto) {
        deleteBooking(bookingUpdateDto.getUserId());


        return saveBooking(bookingUpdateDto);
    }

    @Override
    public void deleteBooking(int bookingId) {
        Booking booking = findBookingById(bookingId);
        booking.setActive(false);
        bookingRepository.save(booking);


    }
}
