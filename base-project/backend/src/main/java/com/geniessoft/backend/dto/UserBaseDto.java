package com.geniessoft.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.geniessoft.backend.utility.customvalidator.ContactNumberConstraint;
import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public abstract class UserBaseDto {

    @NotEmptyOrSpaceOrNull(message = "First name cannot be empty")
    protected String firstName;

    @NotEmptyOrSpaceOrNull(message = "Last name cannot be empty")
    protected String lastName;

    @NotEmpty(message = "Phone number cannot be empty")
    @ContactNumberConstraint
    protected String phoneNumber;

    @NotEmptyOrSpaceOrNull(message = "Email address cannot be empty")
    @Email(message = "Invalid email address")
    private String emailAddress;

    @NotNull(message = "Birth date is mandatory")
    protected LocalDate birthDate;
}
