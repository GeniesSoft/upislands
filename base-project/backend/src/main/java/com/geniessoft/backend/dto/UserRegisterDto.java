package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto extends UserBaseDto {

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 30, message = "Password must be 8-30 characters long")
    private String password;

}