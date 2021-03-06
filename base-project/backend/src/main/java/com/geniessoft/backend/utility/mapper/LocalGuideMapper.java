package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideGetDto;
import com.geniessoft.backend.dto.LocalGuideGetFrontendDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.LocalGuideSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface LocalGuideMapper {

    LocalGuideMapper INSTANCE = Mappers.getMapper(LocalGuideMapper.class);

    LocalGuide localGuideBaseDtoToLocalGuide(LocalGuideBaseDto localGuideBaseDto);

    @Mapping(source = "localGuide.company.companyId", target = "companyId")
    LocalGuideGetDto localGuideToLocalGuideGetDto(LocalGuide localGuide);

    @Mapping(source = "localGuide.company.companyId", target = "companyId")
    @Mapping(source = "localGuide.localGuideId", target = "id")
    @Mapping(source = "localGuide.localGuideName", target = "name")
    LocalGuideGetFrontendDto localGuideToLocalGuideGetFrontendDto(LocalGuide localGuide);

    void updateLocalGuide(@MappingTarget LocalGuide localGuide, LocalGuideUpdateDto localGuideUpdateDto);
}
