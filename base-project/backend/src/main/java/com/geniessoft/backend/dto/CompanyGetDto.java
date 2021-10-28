package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyGetDto extends CompanyBaseDto{

    private int companyId;
    private int userId;
    private List<LocationGetDto> locationGetDtoList;
}
