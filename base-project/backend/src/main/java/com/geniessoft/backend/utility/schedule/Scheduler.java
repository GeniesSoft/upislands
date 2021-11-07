package com.geniessoft.backend.utility.schedule;

import com.geniessoft.backend.model.ScheduleSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Scheduler<T> {

    Map<ScheduleSession, T> updateSchedule(LocalDate day, LocalTime startTime, LocalTime endTime, T t);

}
