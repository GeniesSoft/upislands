package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.Booking;
import com.geniessoft.backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository  extends JpaRepository<Review,Integer> {

    Optional<Review> findReviewByReviewIdAndDeletedIsFalse(Integer integer);
    Optional<Review> findReviewByBooking(Booking booking);
}
