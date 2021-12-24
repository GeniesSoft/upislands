package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideGetDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.LocalGuideSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface LocalGuideMapper {

    LocalGuide localGuideBaseDtoToLocalGuide(LocalGuideBaseDto localGuideBaseDto);

    @Mapping(source = "localGuide.company.companyId", target = "companyId")
    LocalGuideGetDto localGuideToLocalGuideGetDto(LocalGuide localGuide);

    void updateLocalGuide(@MappingTarget LocalGuide localGuide, LocalGuideUpdateDto localGuideUpdateDto);
}
