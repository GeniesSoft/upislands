package com.geniessoft.backend.dto;

import com.geniessoft.backend.utility.customvalidator.NotEmptyOrSpaceOrNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalGuideBaseDto {
    @NotEmptyOrSpaceOrNull(message = "Name cannot be empty.")
    protected String localGuideName;
    protected int companyId;
    protected int localGuideProfileImageId;

}
