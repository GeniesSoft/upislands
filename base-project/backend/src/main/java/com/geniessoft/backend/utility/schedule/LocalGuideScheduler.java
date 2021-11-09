package com.geniessoft.backend.utility.schedule;

import com.geniessoft.backend.model.LocalGuideSession;
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
public class LocalGuideScheduler {

    private final Integer MIN_SESSION_TIME = 30; // in minutes
    private Map<LocalGuideSession,Boolean> scheduleMap;

    public LocalGuideScheduler(Map<LocalGuideSession, Boolean> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    public Map<LocalGuideSession, Boolean> updateSchedule(LocalDate day, LocalTime startTime, LocalTime endTime, Boolean isScheduled) {

        List<LocalGuideSession> sessionList = splitSessions(day, startTime, endTime);

        if (isScheduleUpdatable(sessionList, isScheduled))
            setSchedule(sessionList, isScheduled);
        else
            throw new EntityExistsException("Selected time interval is not suitable for Local Guide");

        return scheduleMap;
    }

    private List<LocalGuideSession> splitSessions(LocalDate day, LocalTime startTime, LocalTime endTime) {
        long sessionCount = Duration.between(startTime, endTime).dividedBy(MIN_SESSION_TIME).toMinutes();
        List<LocalGuideSession> sessionList = new ArrayList<>((int) sessionCount);

        for (int i = 0; i < sessionCount; i++)
            sessionList.add(new LocalGuideSession(day, startTime.plusMinutes((long) i * MIN_SESSION_TIME)));

        return sessionList;
    }

    private boolean isScheduleUpdatable(List<LocalGuideSession> sessionList, Boolean isScheduled) {
        for (LocalGuideSession session: sessionList)
            if (!isSessionAvailable(session, isScheduled))
                return false;

        return true;
    }

    private boolean isSessionAvailable(LocalGuideSession session, Boolean isScheduled) {
        return !scheduleMap.containsKey(session);
    }

    private void setSchedule(List<LocalGuideSession> sessionList, Boolean isScheduled) {
        for (LocalGuideSession session: sessionList) {
            scheduleMap.put(session, isScheduled);
        }
    }

}
