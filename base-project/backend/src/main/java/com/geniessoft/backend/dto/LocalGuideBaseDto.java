package com.geniessoft.backend.dto;

import com.geniessoft.backend.model.Content;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalGuideBaseDto {
    protected String localGuideName;
    protected int companyID;
    protected Content localGuideProfileImageId;
}
