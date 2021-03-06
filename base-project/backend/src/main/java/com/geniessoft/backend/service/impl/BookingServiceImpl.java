package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.BookingBaseDto;
import com.geniessoft.backend.dto.BookingUpdateDto;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.repository.BookingRepository;
import com.geniessoft.backend.service.*;
import com.geniessoft.backend.utility.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper mapper;
    private final CompanyService companyService;
    private final JetSkiDetailsService jetSkiDetailsService;
    private final UserService userService;
    private final LocationService locationService;
    private final LocalGuideService localGuideService;
    private final ScheduleService scheduleService;

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
        LocalGuide localGuide = localGuideService.findLocalGuideById(bookingSaveDto.getLocalGuideId());
        booking.setLocalGuide(localGuide);
        booking.setUser(user);
        booking.setBookingLocation(location);
        booking.setBookingCompany(company);
        scheduleService.updateJetSkiSchedule(company.getCompanyId(), booking.getDate(),booking.getStartTime() , booking.getEndTime(), booking.getJetSkiCount());
        scheduleService.updateLocalGuideSchedule(localGuide.getLocalGuideId(), booking.getDate(),booking.getStartTime() , booking.getEndTime());
        double totalPrice = jetSkiDetailsService.getSessionPrice(company.getCompanyId())*bookingSaveDto.getJetSkiCount();
        booking.setTotalPrice(totalPrice);
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateBooking(BookingUpdateDto bookingUpdateDto) {

        Booking booking = bookingRepository.findById(bookingUpdateDto.getBookingId()).orElseThrow( () ->
                new EntityNotFoundException("Booking Not Found"));

        booking.setActive( bookingUpdateDto.isActive() );
        booking.setPaid( bookingUpdateDto.isPaid() );

        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findAllBookings() {
        Optional<List<Booking>> bookings =  bookingRepository.findAllByActiveIsTrue();
        return bookings.orElseThrow(() -> new EntityNotFoundException("No booking is found."));
    }

    @Override
    public List<Booking> findAllBookingsByLocationOrder() {
        Optional<List<Booking>> bookings =  bookingRepository.findAllByOrderByBookingLocationAsc();
        return bookings.orElseThrow(() -> new EntityNotFoundException("No booking is found."));
    }

    @Override
    public List<Booking> findAllBookingsByLocalGuideOrder() {
        Optional<List<Booking>> bookings =  bookingRepository.findAllByOrderByLocalGuideAsc();
        return bookings.orElseThrow(() -> new EntityNotFoundException("No booking is found."));
    }

    @Override
    public List<Booking> findAllBookingsByUserOrder() {
        Optional<List<Booking>> bookings =  bookingRepository.findAllByOrderByUserAsc();
        return bookings.orElseThrow(() -> new EntityNotFoundException("No booking is found."));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteBooking(int bookingId) {
        Booking booking = findBookingById(bookingId);
        booking.setActive(false);
        bookingRepository.save(booking);
    }
}
