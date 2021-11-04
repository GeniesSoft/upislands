package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewBaseDto {
    protected String reviewMessage;
    protected double locationRating;
    protected double localGuideRating;
    protected int locationId;
    protected int localGuideId;
    protected int bookingId;
    protected int reviewContentId;
}
