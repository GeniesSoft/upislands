package com.geniessoft.backend.service;

import com.geniessoft.backend.model.JetSkiDetails;

import java.time.LocalDate;
import java.time.LocalTime;

public interface JetSkiDetailsService {

    JetSkiDetails findJetSkiDetailsByCompanyId(Integer companyId);
    JetSkiDetails saveJetSkiDetails(Integer companyId, JetSkiDetails jetSkiDetails);
    JetSkiDetails updateJetSkiDetails(Integer companyId, JetSkiDetails jetSkiDetails);
    void deleteJetSkiDetails(Integer companyId);
    void updateSchedule(Integer companyId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkiesToSchedule);
    double getSessionPrice(Integer companyId);

}
