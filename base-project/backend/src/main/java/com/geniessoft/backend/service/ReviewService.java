package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.ReviewBaseDto;
import com.geniessoft.backend.dto.ReviewUpdateDto;
import com.geniessoft.backend.model.Booking;
import com.geniessoft.backend.model.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(ReviewBaseDto reviewBaseDto);
    Review updateReview(ReviewUpdateDto ReviewUpdateDto);
    Review findReviewById(int reviewId);
    List<Review> findReviewsByLocalGuideId(int localGuideId);
    List<Review> findReviewsByLocationId(int locationId);
    void checkIfBookingIsReviewed(Booking booking);
    void deleteReview(int reviewId);

}
