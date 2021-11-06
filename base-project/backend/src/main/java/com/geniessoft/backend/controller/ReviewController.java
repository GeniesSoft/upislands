package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.ReviewBaseDto;
import com.geniessoft.backend.dto.ReviewUpdateDto;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.service.ReviewService;
import com.geniessoft.backend.utility.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
        LocalGuide localGuide = review.getLocalGuide();
        Location location = review.getLocation();
        ReviewBaseDto reviewBaseDto = reviewMapper.reviewToReviewBaseDto(review, booking,location,localGuide);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewBaseDto);

    }


    @GetMapping(value = "/average")
    public ResponseEntity<String> getLocalGuideReviewAverage(@RequestParam(value= "localGuideId") Integer localGuideId){
        Double reviewAverage = reviewService.findReviewAverageByLocalGuideId(localGuideId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewAverage + "");

    }
    @GetMapping(value = "/average")
    public ResponseEntity<String> getLocationReviewAverage(@RequestParam(value= "locationId") Integer locationId){
        Double reviewAverage = reviewService.findReviewAverageByLocationId(locationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewAverage + "");
    }

    @GetMapping
    public ResponseEntity<List<ReviewBaseDto>> getReviewsByLocalGuideId(@RequestParam(value= "localGuideId") Integer localGuideId){
        List<Review> reviews = reviewService.findReviewsByLocalGuideId(localGuideId);
        return getListResponseEntity(reviews);
    }

    @GetMapping
    public ResponseEntity<List<ReviewBaseDto>> getReviewsByLocationId(@RequestParam(value= "location") Integer location){
        List<Review> reviews = reviewService.findReviewsByLocationId(location);
        return getListResponseEntity(reviews);
    }

    private ResponseEntity<List<ReviewBaseDto>> getListResponseEntity(List<Review> reviews) {
        List<ReviewBaseDto> reviewBaseDtoList = new ArrayList<>();
        for (Review review: reviews) {
            ReviewBaseDto reviewBaseDto = reviewMapper.reviewToReviewBaseDto(review, review.getBooking(), review.getLocation(), review.getLocalGuide());
            reviewBaseDtoList.add(reviewBaseDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reviewBaseDtoList);
    }



}
