package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.service.ScheduleService;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleServiceImpl implements ScheduleService {


    @Override
    public void updateJetSkiSchedule(Integer companyId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkis) {

    }

    @Override
    public void updateLocalGuideSchedule(Integer localGuideId, LocalDate day, LocalTime startTime, LocalTime endTime) {

    }

    @Override
    public LocalGuide getAvailableLocalGuide(Integer localGuideId) {
        return null;
    }

    @Override
    public Integer getJetSkiCount(Integer localGuideId) {
        return null;
    }

}
