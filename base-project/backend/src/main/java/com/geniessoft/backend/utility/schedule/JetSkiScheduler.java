package com.geniessoft.backend.utility.schedule;

import com.geniessoft.backend.model.JetSkiSession;
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
public class JetSkiScheduler {

    private final Integer MIN_SESSION_TIME = 30; // in minutes
    private Integer totalNumOfJetSkies;
    private Map<JetSkiSession,Integer> scheduleMap;

    public JetSkiScheduler(Map<JetSkiSession, Integer> scheduleMap, Integer totalNumOfJetSkies) {
        this.totalNumOfJetSkies = totalNumOfJetSkies;
        this.scheduleMap = scheduleMap;
    }

    public Map<JetSkiSession,Integer> updateSchedule(LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkiesToSchedule) {

        List<JetSkiSession> sessionList = splitSessions(day, startTime, endTime);

        if (isScheduleUpdatable(sessionList, numOfJetSkiesToSchedule))
            setSchedule(sessionList, numOfJetSkiesToSchedule);
        else
            throw new EntityExistsException("Selected time interval is not suitable. Number of available jet skis is not enough");

        return scheduleMap;
    }

    private List<JetSkiSession> splitSessions(LocalDate day, LocalTime startTime, LocalTime endTime) {
        long sessionCount = Duration.between(startTime, endTime).dividedBy(MIN_SESSION_TIME).toMinutes();
        List<JetSkiSession> sessionList = new ArrayList<>((int) sessionCount);

        for (int i = 0; i < sessionCount; i++)
            sessionList.add(new JetSkiSession(day, startTime.plusMinutes((long) i * MIN_SESSION_TIME)));

        return sessionList;
    }

    private boolean isScheduleUpdatable(List<JetSkiSession> sessionList, Integer numOfJetSkiesToSchedule) {
        for (JetSkiSession session: sessionList)
            if (!isSessionAvailable(session, numOfJetSkiesToSchedule))
                return false;

        return true;
    }

    private boolean isSessionAvailable(JetSkiSession session, Integer numOfJetSkiesToSchedule) {
        if (scheduleMap.containsKey(session))
            return (numOfJetSkiesToSchedule <= (totalNumOfJetSkies - scheduleMap.get(session)));
        else
            return true;
    }

    private void setSchedule(List<JetSkiSession> sessionList, Integer numOfJetSkiesToSchedule) {
        for (JetSkiSession session: sessionList) {
            if (scheduleMap.containsKey(session))
                scheduleMap.put(session, scheduleMap.get(session) + numOfJetSkiesToSchedule);
            else
                scheduleMap.put(session, numOfJetSkiesToSchedule);
        }
    }

}
