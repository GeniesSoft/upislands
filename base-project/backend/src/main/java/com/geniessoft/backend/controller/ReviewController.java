package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.ProfileImageDto;
import com.geniessoft.backend.dto.ReviewBaseDto;
import com.geniessoft.backend.dto.ReviewUpdateDto;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.service.ReviewService;
import com.geniessoft.backend.utility.customvalidator.ImageConstraint;
import com.geniessoft.backend.utility.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public String addReview(@Valid @RequestBody ReviewBaseDto reviewBaseDto){
        reviewService.saveReview(reviewBaseDto);
        return "Review is saved";
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public String updateReview(@Valid @RequestBody ReviewUpdateDto reviewUpdateDto){
        reviewService.updateReview(reviewUpdateDto);
        return "Review is updated";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public String deleteReview(@PathVariable(value = "id") Integer reviewId){
        reviewService.deleteReview(reviewId);
        return "Review is deleted";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ReviewBaseDto getReviewById(@PathVariable(value = "id") Integer reviewId ){
        Review review = reviewService.findReviewById(reviewId);
        Booking booking = review.getBooking();
        LocalGuide localGuide = review.getLocalGuide();
        Location location = review.getLocation();
        ReviewBaseDto reviewBaseDto = reviewMapper.reviewToReviewBaseDto(review, booking,location,localGuide);
        return reviewBaseDto;

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "review/average/local-guide")
    public String getLocalGuideReviewAverage(@RequestParam(value= "localGuideId") Integer localGuideId){
        Double reviewAverage = reviewService.findReviewAverageByLocalGuideId(localGuideId);
        return reviewAverage + "";

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "review/average/location")
    public String getLocationReviewAverage(@RequestParam(value= "locationId") Integer locationId){
        Double reviewAverage = reviewService.findReviewAverageByLocationId(locationId);
        return reviewAverage + "";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "review/local-guide-reviews")
    public List<ReviewBaseDto> getReviewsByLocalGuideId(@RequestParam(value= "localGuideId") Integer localGuideId){
        List<Review> reviews = reviewService.findReviewsByLocalGuideId(localGuideId);
        return getListResponseEntity(reviews);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "review/location-reviews")
    public List<ReviewBaseDto> getReviewsByLocationId(@RequestParam(value= "location") Integer location){
        List<Review> reviews = reviewService.findReviewsByLocationId(location);
        return getListResponseEntity(reviews);
    }


    private List<ReviewBaseDto> getListResponseEntity(List<Review> reviews) {
        List<ReviewBaseDto> reviewBaseDtoList = new ArrayList<>();
        for (Review review: reviews) {
            ReviewBaseDto reviewBaseDto = reviewMapper.reviewToReviewBaseDto(review, review.getBooking(), review.getLocation(), review.getLocalGuide());
            reviewBaseDtoList.add(reviewBaseDto);
        }
        return reviewBaseDtoList;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(
            path = "{reviewId}/content",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public String addReviewContent(
            @PathVariable("reviewId") int reviewId,
            @RequestParam("file") @ImageConstraint MultipartFile file){
        reviewService.addReviewContent(reviewId ,file);
        return "Review content is uploaded.";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{reviewId}/content")
    public ProfileImageDto getReviewContent(
            @PathVariable("reviewId") int reviewId){

        byte[] image = reviewService.getReviewContent(reviewId);
        return new ProfileImageDto(image);
    }
}
