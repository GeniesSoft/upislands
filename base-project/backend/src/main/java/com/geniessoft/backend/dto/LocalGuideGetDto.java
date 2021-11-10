package com.geniessoft.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
public class LocalGuideGetDto extends LocalGuideBaseDto{
    private List<LocalDate> days;
    private List<LocalTime> startTimes;
    private List<Boolean> isScheduledList;
}
