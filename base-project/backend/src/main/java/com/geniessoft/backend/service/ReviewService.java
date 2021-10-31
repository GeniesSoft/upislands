package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.ReviewBaseDto;
import com.geniessoft.backend.dto.ReviewUpdateDto;
import com.geniessoft.backend.model.Booking;
import com.geniessoft.backend.model.Review;

public interface ReviewService {
    Review saveReview(ReviewBaseDto reviewBaseDto);
    Review updateReview(ReviewUpdateDto ReviewUpdateDto);
    void deleteReview(int reviewId);
    Review findReviewById(int reviewId);
    void checkIfBookingIsReviewed(Booking booking);
}
