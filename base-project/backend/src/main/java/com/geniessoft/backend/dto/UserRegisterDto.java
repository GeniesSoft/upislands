package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegisterDto extends UserDtoBase{

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 10, message = "Password must be 8-10 characters long.")
    private String password;

    @NotEmptyOrSpaceOrNull(message = "Email address cannot be empty.")
    @Email(message = "It is not convenient email address.")
    private String emailAddress;
}
