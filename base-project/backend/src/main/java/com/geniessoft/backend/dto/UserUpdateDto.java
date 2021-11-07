package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class UserUpdateDto extends UserBaseDto {

    @Positive(message = "User Id must provide")
    private int userId;

}
