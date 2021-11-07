package com.geniessoft.backend.utility.schedule;

import com.geniessoft.backend.model.ScheduleSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
@NoArgsConstructor
public abstract class SchedulerBase<T> implements Scheduler<T> {

    protected Integer MIN_SESSION_TIME = 30; // in minutes
    protected Map<ScheduleSession, T> scheduleMap;

    public SchedulerBase(Integer minSessionTime, Map<ScheduleSession, T> scheduleMap) {
        this.MIN_SESSION_TIME = minSessionTime;
        this.scheduleMap = scheduleMap;
    }

    public Map<ScheduleSession, T> updateSchedule(LocalDate day, LocalTime startTime, LocalTime endTime, T t) {
        List<ScheduleSession> sessionList = splitSessions(day, startTime, endTime);

        if (isScheduleUpdatable(sessionList, t))
            setSchedule(sessionList, t);
        else
            throw new EntityExistsException("Selected time interval is not suitable");

        return scheduleMap;
    }

    protected List<ScheduleSession> splitSessions(LocalDate day, LocalTime startTime, LocalTime endTime) {
        long sessionCount = Duration.between(startTime, endTime).dividedBy(MIN_SESSION_TIME).toMinutes();
        List<ScheduleSession> sessionList = new ArrayList<>((int) sessionCount);

        for (int i = 0; i < sessionCount; i++)
            sessionList.add(new ScheduleSession(day, startTime.plusMinutes((long) i * MIN_SESSION_TIME)));

        return sessionList;
    }

    protected boolean isScheduleUpdatable(List<ScheduleSession> sessionList, T t) {
        for (ScheduleSession session: sessionList)
            if (!isSessionAvailable(session, t))
                return false;

        return true;
    }

    protected abstract boolean isSessionAvailable(ScheduleSession session, T t);

    protected abstract void setSchedule(List<ScheduleSession> sessionList, T t);

}
