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
public class IntegerScheduler extends SchedulerBase<Integer> {

    private Integer totalAvailable;

    public IntegerScheduler(Map<ScheduleSession, Integer> scheduleMap, Integer totalAvailable) {
        this(30, scheduleMap, totalAvailable);
    }

    public IntegerScheduler(Integer minSessionTime, Map<ScheduleSession, Integer> scheduleMap, Integer totalAvailable) {
        super(minSessionTime, scheduleMap);
        this.totalAvailable = totalAvailable;
    }

    @Override
    protected boolean isSessionAvailable(ScheduleSession session, Integer numOfObjectsToSchedule) {
        if (scheduleMap.containsKey(session))
            return (numOfObjectsToSchedule <= (totalAvailable - scheduleMap.get(session)));
        else
            return true;
    }

    @Override
    protected void setSchedule(List<ScheduleSession> sessionList, Integer numOfObjectsToSchedule) {
        for (ScheduleSession session: sessionList) {
            if (scheduleMap.containsKey(session))
                scheduleMap.put(session, scheduleMap.get(session) + numOfObjectsToSchedule);
            else
                scheduleMap.put(session, numOfObjectsToSchedule);
        }
    }

}
