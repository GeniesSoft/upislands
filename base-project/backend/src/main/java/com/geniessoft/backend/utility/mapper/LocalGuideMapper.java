package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.LocalGuide;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LocalGuideMapper {

    LocalGuide localGuideBaseDtoToLocalGuide(LocalGuideBaseDto localGuideBaseDto);
    void updateLocalGuide(@MappingTarget LocalGuide localGuide, LocalGuideUpdateDto localGuideUpdateDto);
}
