package com.geniessoft.backend.service;

import com.geniessoft.backend.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleService {

    void updateJetSkiSchedule(Integer companyId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkis);
    void updateLocalGuideSchedule(Integer localGuideId, LocalDate day, LocalTime startTime, LocalTime endTime);
    List<LocalGuide> getAvailableLocalGuides(Integer locationId, LocalDate day, LocalTime startTime, LocalTime endTime);
    LocalGuideSession getLocalGuideScheduleByLocalGuideId(Integer localGuideId);
    Integer getJetSkiCount(LocalGuide localGuide);
    void deleteSession(Booking booking);
}
