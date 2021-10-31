package com.geniessoft.backend.service;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.JetSkiDetails;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

public interface JetSkiDetailsService {

    JetSkiDetails findJetSkiDetailsById(int jetSkiDetailsId);
    JetSkiDetails findJetSkiDetailsByCompany(Company company);
    JetSkiDetails saveJetSkiDetails(JetSkiDetails jetSkiDetails);
    JetSkiDetails updateJetSkiDetails(JetSkiDetails jetSkiDetails);
    void deleteJetSkiDetails(Integer jetSkiDetailsId);
    void updateSchedule(Integer companyId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkiesToSchedule);
    double getSessionPrice(Company company);

}
