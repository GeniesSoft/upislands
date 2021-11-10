package com.geniessoft.backend.dto;

import com.geniessoft.backend.model.LocalGuideSession;
import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LocalGuideBaseDto {
    @NotEmptyOrSpaceOrNull(message = "Name cannot be empty.")
    protected String localGuideName;
    protected int companyId;
    protected int localGuideProfileImageId;


}
