package com.geniessoft.backend.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class UserUpdateDto extends UserDtoBase{

    @Positive
    private int userId;
    private String emailAddress;
}
