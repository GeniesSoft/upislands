package com.geniessoft.backend.service;

import com.geniessoft.backend.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ScheduleService {

    void updateJetSkiSchedule(Integer companyId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkis);
    void updateLocalGuideSchedule(Integer localGuideId, LocalDate day, LocalTime startTime, LocalTime endTime);
    List<LocalGuide> getAvailableLocalGuides(Integer locationId, LocalDate day, LocalTime startTime, LocalTime endTime);
    Map<LocalGuideSession,Boolean> getLocalGuideScheduleByLocalGuideId(Integer localGuideId);
    Integer getAvailableJetSkiCount(LocalGuide localGuide,LocalDate day, LocalTime startTime, LocalTime endTime);
    void deleteSession(Booking booking);
}
