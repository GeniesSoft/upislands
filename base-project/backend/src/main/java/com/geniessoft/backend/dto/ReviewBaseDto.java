package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewBaseDto {
    protected String reviewMessage;
    protected double companyRating;
    protected double locationRating;
    protected double localGuideRating;
    protected int bookingId;
    protected int reviewContentId;
}
