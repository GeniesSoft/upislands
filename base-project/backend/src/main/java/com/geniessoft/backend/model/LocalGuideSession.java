package com.geniessoft.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalGuideSession {


    private LocalDate day;
    private LocalTime startTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalGuideSession that = (LocalGuideSession) o;
        return day.equals(that.day) && startTime.equals(that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, startTime);
    }
}
