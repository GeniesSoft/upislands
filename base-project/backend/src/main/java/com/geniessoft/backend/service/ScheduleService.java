package com.geniessoft.backend.service;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleService {

    void updateJetSkiSchedule(Integer companyId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkis);
    void updateLocalGuideSchedule(Integer localGuideId, LocalDate day, LocalTime startTime, LocalTime endTime);
    LocalGuide getAvailableLocalGuide(Integer localGuideId);
    Integer getJetSkiCount(Integer localGuideId);
}
