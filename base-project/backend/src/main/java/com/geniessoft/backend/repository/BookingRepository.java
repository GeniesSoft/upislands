package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    Optional<Booking> findBookingByBookingIdAndActiveIsTrue(Integer bookingId);
}
