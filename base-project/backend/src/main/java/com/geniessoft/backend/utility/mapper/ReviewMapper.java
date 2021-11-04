package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.ReviewBaseDto;
import com.geniessoft.backend.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel="spring")
public interface ReviewMapper {
    Review reviewDtoToReview(ReviewBaseDto reviewBaseDto);
    ReviewBaseDto reviewToReviewBaseDto(Review review, Booking booking, Location location, LocalGuide localGuide);
    void updateReview(@MappingTarget Review review, ReviewBaseDto reviewBaseDto);

}
