package com.geniessoft.backend.dto;


import com.geniessoft.backend.utility.customvalidator.LocationListConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public  class BookingBaseDto {

    protected Date startDate;
    protected Date finishDate;
    protected double totalPrice;
    protected int locationId;
    protected boolean active;
    protected int userId;

    @LocationListConstraint(message = "You must select at least 1 jet ski")
    private List<Integer> jetSkiIdList;


}
