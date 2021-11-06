package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterDto extends UserDtoBase{

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "Password must be 8-30 characters long.")
    private String password;

    @NotEmptyOrSpaceOrNull(message = "Email address cannot be empty.")
    @Email(message = "It is not convenient email address.")
    private String emailAddress;
}
