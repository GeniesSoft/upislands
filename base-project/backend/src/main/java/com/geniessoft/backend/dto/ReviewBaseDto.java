package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewBaseDto {
    protected String reviewMessage;
    protected double rating;
    protected int bookingId;
    protected boolean deleted;
}
