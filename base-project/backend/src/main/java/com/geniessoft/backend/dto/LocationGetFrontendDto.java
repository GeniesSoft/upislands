package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocationGetFrontendDto {

    private int id;
    private String title;
    private int slug;
    private String content;

    private int agentId = 0;
    private String status = "Active";
    private String price = "Price";
    private boolean isNegotiable = false;
    private String propertyType = "Default Property Type";
    private String condition = "Default Condition";

    private AddressDto location = AddressDto.DEFAULT_ADDRESS;

    private int rating = 5;
    private int ratingCount = 1;

    private String contactNumber = "Default Contact Number";
    private String image = "";

    private List<GalleryItem> videoGallery;
    private List<GalleryItem> gallery;
    private List<ReviewGetFrontendDto> reviews = List.of();

}
