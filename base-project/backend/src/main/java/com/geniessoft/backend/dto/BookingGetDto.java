package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingGetDto extends BookingBaseDto {

    private int bookingId;
    private boolean active;
    private boolean paid;

}
