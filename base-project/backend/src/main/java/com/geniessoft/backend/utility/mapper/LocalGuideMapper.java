package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideGetDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.LocalGuideSession;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface LocalGuideMapper {

    LocalGuide localGuideBaseDtoToLocalGuide(LocalGuideBaseDto localGuideBaseDto);
    LocalGuideGetDto localGuideToLocalGuideGetDto(LocalGuide localGuide, Company company, List<LocalDate> days, List<LocalTime> startTimes, List<Boolean> isScheduledList);
    void updateLocalGuide(@MappingTarget LocalGuide localGuide, LocalGuideUpdateDto localGuideUpdateDto);
}
