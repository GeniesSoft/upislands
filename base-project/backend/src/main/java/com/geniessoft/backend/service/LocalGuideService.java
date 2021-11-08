package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.LocalGuide;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface LocalGuideService {
    LocalGuide findLocalGuideById(int localGuideId);
    LocalGuide updateLocalGuide(LocalGuideUpdateDto localGuideUpdateDto);
    LocalGuide DeleteLocalGuide(int localGuideId);
    LocalGuide saveLocalGuide(LocalGuideBaseDto localGuideBaseDto);
    List<LocalGuide> findAllLocalGuides();
    public void updateSchedule(Integer localGuideId, LocalDate day, LocalTime startTime, LocalTime endTime);
}
