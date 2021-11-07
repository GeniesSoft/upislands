package com.geniessoft.backend.utility.schedule;

import com.geniessoft.backend.model.ScheduleSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
@NoArgsConstructor
public class BooleanScheduler extends SchedulerBase<Boolean> {

    public BooleanScheduler(Map<ScheduleSession, Boolean> scheduleMap) {
        this(30, scheduleMap);
    }

    public BooleanScheduler(Integer minSessionTime, Map<ScheduleSession, Boolean> scheduleMap) {
        super(minSessionTime, scheduleMap);
    }

    @Override
    protected boolean isSessionAvailable(ScheduleSession session, Boolean isScheduled) {
        return scheduleMap.getOrDefault(session, true);
    }

    @Override
    protected void setSchedule(List<ScheduleSession> sessionList, Boolean isScheduled) {
        for (ScheduleSession scheduleSession : sessionList) {
            scheduleMap.put(scheduleSession, isScheduled);
        }

    }
}
