package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.JSONObject;
import java.util.List;

@Getter
@Setter
public class LocalGuideGetFrontendDto {

    private int id;
    private String name;
    private String content = "Default Content";
    private String image = "/images/coming-soon.png";

    private int companyId;

    private int rating = 5;
    private int ratingCount = 1;
    private String price = "default price";
    private List<GalleryItem> gallery = List.of();
    private List<GalleryItem> videoGallery = List.of();
    private AddressDto location = AddressDto.DEFAULT_ADDRESS;
    private JSONObject amenities;
    {
        amenities = new JSONObject();

        JSONObject deposit = new JSONObject();
        deposit.put("value", true);

        JSONObject personalItemLocator = new JSONObject();
        personalItemLocator.put("value", true);

        JSONObject gas = new JSONObject();
        gas.put("value", true);

        JSONObject secondRiderFee = new JSONObject();
        secondRiderFee.put("value", true);

        amenities.put("deposit", deposit);
        amenities.put("personalItemLocator", personalItemLocator);
        amenities.put("gas", gas);
        amenities.put("secondRiderFee", secondRiderFee);
    }
    private List<ReviewGetFrontendDto> reviews = List.of();

}
