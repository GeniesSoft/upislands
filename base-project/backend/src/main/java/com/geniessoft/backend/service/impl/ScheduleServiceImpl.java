package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.*;
import com.geniessoft.backend.service.JetSkiDetailsService;
import com.geniessoft.backend.service.LocalGuideService;
import com.geniessoft.backend.service.ScheduleService;
import com.geniessoft.backend.utility.schedule.JetSkiScheduler;
import com.geniessoft.backend.utility.schedule.LocalGuideScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final LocalGuideService localGuideService;
    private final JetSkiDetailsService jetSkiDetailsService;
    private final LocalGuideScheduler localGuideScheduler;
    private final JetSkiScheduler jetSkiScheduler;

    @Transactional
    @Override
    public void updateJetSkiSchedule(Integer companyId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkis) {
        JetSkiDetails jetSkiDetails = jetSkiDetailsService.findJetSkiDetailsByCompanyId(companyId);

        jetSkiScheduler.setScheduleMap(jetSkiDetails.getScheduleMap());
        jetSkiScheduler.setTotalNumOfJetSkies(jetSkiDetails.getTotalNumberOfJetSkies());
        jetSkiDetails.setScheduleMap(jetSkiScheduler.updateSchedule(day, startTime, endTime, numOfJetSkis));
        jetSkiDetailsService.updateSchedule(jetSkiDetails);
    }
    @Transactional
    @Override
    public void updateLocalGuideSchedule(Integer localGuideId, LocalDate day, LocalTime startTime, LocalTime endTime) {
        LocalGuide localGuide = localGuideService.findLocalGuideById(localGuideId);

        localGuideScheduler.setScheduleMap(localGuide.getScheduleMap());
        localGuide.setScheduleMap(localGuideScheduler.updateSchedule(day, startTime, endTime, true));
        localGuideService.updateSchedule(localGuide);
    }

    @Override
    public List<LocalGuide> getAvailableLocalGuides(Integer locationId, LocalDate day, LocalTime startTime, LocalTime endTime) {
        localGuideService.getLocalGuidesByLocationId(locationId);
        Map scheduleMap = localGuideScheduler.getScheduleMap();
        return null;
    }

    @Override
    public LocalGuideSession getLocalGuideScheduleByLocalGuideId(Integer localGuideId) {
        return null;
    }


    @Override
    public Integer getJetSkiCount(LocalGuide localGuide) {
        return null;
    }

    @Transactional
    @Override
    public void deleteSession(Booking booking) {

    }


}