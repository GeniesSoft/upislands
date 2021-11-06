package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.ReviewBaseDto;
import com.geniessoft.backend.dto.ReviewUpdateDto;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.repository.ReviewRepository;
import com.geniessoft.backend.service.*;
import com.geniessoft.backend.utility.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final BookingService bookingService;
    private final LocationService locationService;
    private final LocalGuideService localGuideService;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Review saveReview(ReviewBaseDto reviewBaseDto) {
        Review review = reviewMapper.reviewDtoToReview(reviewBaseDto);
        Booking booking = bookingService.findBookingById(reviewBaseDto.getBookingId());
        checkIfBookingIsReviewed(booking);
        review.setBooking(booking);
        reviewRepository.save(review);
        return review;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Review updateReview(ReviewUpdateDto reviewUpdateDto) {
        Review review = findReviewById(reviewUpdateDto.getReviewId());
        reviewMapper.updateReview(review, reviewUpdateDto);
        reviewRepository.save(review);
        return review;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteReview(int reviewId) {
        Review review = findReviewById(reviewId);
        review.setDeleted(true);
        reviewRepository.save(review);
    }

    @Override
    public Review findReviewById(int reviewId) {
        Optional<Review> review = reviewRepository.findReviewByReviewIdAndDeletedIsFalse(reviewId);

        return review.orElseThrow(() -> new EntityNotFoundException("Review is not found."));
    }

    @Override
    public List<Review> findReviewsByLocalGuideId(int localGuideId) {
        Optional<List<Review>> reviews = reviewRepository.findReviewsByLocalGuide(localGuideService.findLocalGuideById(localGuideId));
        return reviews.orElseThrow(() -> new EntityNotFoundException("No local guide review found."));
    }
    @Override
    public Double findReviewAverageByLocalGuideId(int localGuideId){
        List<Review> reviews = findReviewsByLocalGuideId(localGuideId);
        double reviewSum = 0;
        for (Review review: reviews) {
            reviewSum += review.getLocalGuideRating();
        }
        double reviewAverage = reviewSum/reviews.size();
        return reviewAverage;
    }

    @Override
    public Double findReviewAverageByLocationId(int locationId) {
        List<Review> reviews = findReviewsByLocationId(locationId);
        double reviewSum = 0;
        for (Review review: reviews) {
            reviewSum += review.getLocalGuideRating();
        }
        double reviewAverage = reviewSum/reviews.size();
        return reviewAverage;

    }

    @Override
    public List<Review> findReviewsByLocationId(int locationId) {
        Optional<List<Review>> reviews = reviewRepository.findReviewsByLocation(locationService.findLocationById(locationId));
        return reviews.orElseThrow(() -> new EntityNotFoundException("No location review found."));
    }


    @Override
    public void checkIfBookingIsReviewed(Booking booking) {
        if (reviewRepository.findReviewByBooking(booking).isPresent()){
            throw new EntityExistsException("This booking is already reviewed");
        }

    }
}
