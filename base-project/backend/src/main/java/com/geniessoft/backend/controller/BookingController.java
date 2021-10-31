package com.geniessoft.backend.controller;


import com.geniessoft.backend.dto.BookingBaseDto;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper mapper;

    @PostMapping(value = "/save")
    public ResponseEntity<String> addBooking(@Valid @RequestBody BookingBaseDto bookingBaseDto){
        bookingService.saveBooking(bookingBaseDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Booking is saved");
    }

    @PutMapping
    public ResponseEntity<String> updateBooking(@Valid @RequestBody BookingUpdateDto bookingUpdateDto){
        bookingService.updateBooking(bookingUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Booking is updated");

    }
    @DeleteMapping
    public ResponseEntity<String> deleteBooking(@RequestParam(value = "bookingId") Integer bookingId){
        bookingService.deleteBooking(bookingId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Booking is deleted");

    }
    @GetMapping
    public ResponseEntity<BookingBaseDto> getBookingById(@RequestParam( value = "bookingId") Integer bookingId){
        Booking booking = bookingService.findBookingById(bookingId);
        Location location = booking.getBookingLocation();
        Company company = booking.getBookingCompany();
        User user = booking.getUser();
        BookingBaseDto dto = mapper.bookingToBookingBaseDto(booking, location, company, user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }
}
