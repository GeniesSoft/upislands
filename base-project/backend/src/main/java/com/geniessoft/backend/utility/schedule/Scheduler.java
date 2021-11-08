package com.geniessoft.backend.utility.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Scheduler<T> {

    void updateSchedule(LocalDate day, LocalTime startTime, LocalTime endTime, T t);

}
