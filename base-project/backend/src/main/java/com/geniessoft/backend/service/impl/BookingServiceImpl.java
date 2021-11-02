package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.BookingBaseDto;
import com.geniessoft.backend.dto.BookingUpdateDto;
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
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

   private final BookingRepository bookingRepository;
   private final BookingMapper mapper;
   private final UserService userService;
   private final LocationService locationService;
   private final CompanyService companyService;
    private final JetSkiDetailsService jetSkiDetailsService;

    @Override
    public Booking findBookingById(int bookingId) {
        Optional<Booking> booking = bookingRepository.findBookingByBookingIdAndActiveIsTrue(bookingId);
        return booking.orElseThrow(() -> new EntityNotFoundException("Booking is not found."));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Booking saveBooking(BookingBaseDto bookingSaveDto) {

        User user = userService.findUser(bookingSaveDto.getUserId());
        Location location = locationService.findLocationById(bookingSaveDto.getLocationId());
        Booking booking = mapper.bookingDtoToBooking(bookingSaveDto);
        Company company = companyService.findCompanyById(bookingSaveDto.getCompanyId());
        booking.setUser(user);
        booking.setBookingLocation(location);
        booking.setBookingCompany(company);
        jetSkiDetailsService.updateSchedule(company.getCompanyId(), booking.getDate(),booking.getStartTime() , booking.getEndTime(), booking.getJetSkiCount());
        double totalPrice = jetSkiDetailsService.getSessionPrice(company);
        booking.setTotalPrice(totalPrice);
        bookingRepository.save(booking);
        return booking;
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public Booking updateBooking(BookingUpdateDto bookingUpdateDto) {
        deleteBooking(bookingUpdateDto.getBookingId());
        BookingBaseDto bookingBaseDto = mapper.bookingUpdateDtoToBookingBaseDto(bookingUpdateDto);

        return saveBooking(bookingBaseDto);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteBooking(int bookingId) {
        Booking booking = findBookingById(bookingId);
        booking.setActive(false);
        bookingRepository.save(booking);


    }
}
