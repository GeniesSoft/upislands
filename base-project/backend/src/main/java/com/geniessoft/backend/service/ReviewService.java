package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.ReviewBaseDto;
import com.geniessoft.backend.dto.ReviewUpdateDto;
import com.geniessoft.backend.model.Booking;
import com.geniessoft.backend.model.Review;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    Review saveReview(ReviewBaseDto reviewBaseDto);
    Review updateReview(ReviewUpdateDto ReviewUpdateDto);
    Review findReviewById(int reviewId);
    List<Review> findReviewsByLocalGuideId(int localGuideId);
    List<Review> findReviewsByLocationId(int locationId);
    Double findReviewAverageByLocalGuideId(int localGuideId);
    Double findReviewAverageByLocationId(int locationId);
    void checkIfBookingIsReviewed(Booking booking);
    void deleteReview(int reviewId);
    void addReviewContent(int reviewId, MultipartFile file);
}
