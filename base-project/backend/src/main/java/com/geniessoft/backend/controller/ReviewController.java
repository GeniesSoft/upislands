package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.ReviewBaseDto;
import com.geniessoft.backend.dto.ReviewUpdateDto;
import com.geniessoft.backend.model.Booking;
import com.geniessoft.backend.model.Review;
import com.geniessoft.backend.service.ReviewService;
import com.geniessoft.backend.utility.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping(value = "/save")
    public ResponseEntity<String> addReview(@Valid @RequestBody ReviewBaseDto reviewBaseDto){
        reviewService.saveReview(reviewBaseDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Review is saved");
    }

    @PutMapping
    public ResponseEntity<String> updateReview(@Valid @RequestBody ReviewUpdateDto reviewUpdateDto){
        reviewService.updateReview(reviewUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Review is updated");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteReview(@RequestParam(value = "reviewId") Integer reviewId){
        reviewService.deleteReview(reviewId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Review is deleted");
    }
    @GetMapping
    public ResponseEntity<ReviewBaseDto> getReviewById(@RequestParam(value = "reviewId") Integer reviewId ){
        Review review = reviewService.findReviewById(reviewId);
        Booking booking = review.getBooking();
        ReviewBaseDto reviewBaseDto = reviewMapper.reviewToReviewBaseDto(review, booking);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewBaseDto);

    }
}
