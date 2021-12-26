package com.geniessoft.backend.controller;


import com.geniessoft.backend.dto.BookingBaseDto;
import com.geniessoft.backend.dto.BookingGetDto;
import com.geniessoft.backend.dto.BookingUpdateDto;
import com.geniessoft.backend.model.Booking;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.service.BookingService;
import com.geniessoft.backend.utility.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper mapper;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String addBooking(@Valid @RequestBody BookingBaseDto bookingBaseDto){
        bookingService.saveBooking(bookingBaseDto);
        return "Booking is saved";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<BookingGetDto> getBookings() {
        return bookingService.findAllBookings().stream().map(mapper::bookingToBookingGetDto).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String updateBooking(@Valid @RequestBody BookingUpdateDto bookingUpdateDto){
        bookingService.updateBooking(bookingUpdateDto);
        return "Booking is updated";

    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String deleteBooking(@PathVariable(value = "id") Integer bookingId){
        bookingService.deleteBooking(bookingId);
        return "Booking is deleted";

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public BookingBaseDto getBookingById(@PathVariable( value = "id") Integer bookingId){
        Booking booking = bookingService.findBookingById(bookingId);
        Location location = booking.getBookingLocation();
        Company company = booking.getBookingCompany();
        User user = booking.getUser();
        BookingBaseDto dto = mapper.bookingToBookingBaseDto(booking, location, company, user);
        return dto;
    }
}
