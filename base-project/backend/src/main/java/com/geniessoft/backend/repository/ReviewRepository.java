package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository  extends JpaRepository<Review,Integer> {

    Optional<Review> findReviewByReviewIdAndDeletedIsFalse(Integer integer);
    Optional<Review> findReviewByBooking(Booking booking);
    Optional<List<Review>> findReviewsByLocalGuide(LocalGuide localGuide);
    Optional<List<Review>> findReviewsByLocation(Location location);
}
