package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.JetSkiDetails;
import com.geniessoft.backend.repository.CompanyRepository;
import com.geniessoft.backend.repository.JetSkiDetailsRepository;
import com.geniessoft.backend.utility.schedule.Scheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class JetSkiDetailsServiceImpl implements com.geniessoft.backend.service.JetSkiDetailsService {

    private final Scheduler scheduler;
    private final JetSkiDetailsRepository jetSkiDetailsRepository;
    private final CompanyRepository companyRepository;

    @Override
    public JetSkiDetails findJetSkiDetailsById(int jetSkiDetailsId) {
        return jetSkiDetailsRepository.findById(jetSkiDetailsId).orElseThrow(
                () -> new EntityNotFoundException("Jet ski details not found")
        );
    }

    @Override
    public JetSkiDetails findJetSkiDetailsByCompany(Company company) {
        return jetSkiDetailsRepository.findByCompany(company).orElseThrow(
                () -> new EntityNotFoundException("Jet ski details not found")
        );
    }

    @Override
    @Transactional
    public JetSkiDetails saveJetSkiDetails(JetSkiDetails jetSkiDetails) {
        return jetSkiDetailsRepository.save(jetSkiDetails);
    }

    @Override
    @Transactional
    public JetSkiDetails updateJetSkiDetails(JetSkiDetails jetSkiDetails) {
        return jetSkiDetailsRepository.save(jetSkiDetails);
    }

    @Override
    @Transactional
    public void deleteJetSkiDetails(Integer jetSkiDetailsId) {
        jetSkiDetailsRepository.deleteById(jetSkiDetailsId);
    }

    @Override
    @Transactional
    public void updateSchedule(Integer companyId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkiesToSchedule) {
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new EntityNotFoundException("Company Not Found")
        );
        JetSkiDetails jetSkiDetails = findJetSkiDetailsByCompany(company);

        scheduler.setScheduleMap(jetSkiDetails.getScheduleMap());
        scheduler.setTotalNumOfJetSkies(jetSkiDetails.getTotalNumberOfJetSkies());

        jetSkiDetails.setScheduleMap(scheduler.updateSchedule(day, startTime, endTime, numOfJetSkiesToSchedule));
        saveJetSkiDetails(jetSkiDetails);
    }

    @Override
    public double getSessionPrice(Company company){
        JetSkiDetails jetSkiDetails = findJetSkiDetailsByCompany(company);
        return jetSkiDetails.getSessionPrice();
    }

}
