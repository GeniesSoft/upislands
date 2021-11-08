package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.JetSkiDetails;
import com.geniessoft.backend.repository.CompanyRepository;
import com.geniessoft.backend.repository.JetSkiDetailsRepository;
import com.geniessoft.backend.utility.schedule.IntegerScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JetSkiDetailsServiceImpl implements com.geniessoft.backend.service.JetSkiDetailsService {

    private final IntegerScheduler integerScheduler;
    private final JetSkiDetailsRepository jetSkiDetailsRepository;
    private final CompanyRepository companyRepository;

    @Override
    public JetSkiDetails findJetSkiDetailsByCompanyId(Integer companyId) {
        return jetSkiDetailsRepository.findByCompany_CompanyId(companyId).orElseThrow(
                () -> new EntityNotFoundException("Jet ski details not found")
        );
    }

    @Override
    @Transactional
    public JetSkiDetails saveJetSkiDetails(Integer companyId, JetSkiDetails jetSkiDetails) {
        Optional<JetSkiDetails> jetSkiDetailsOptional = jetSkiDetailsRepository.findByCompany_CompanyId(companyId);
        if (jetSkiDetailsOptional.isPresent())
            throw new EntityExistsException("Jet ski details already exists");

        jetSkiDetails.setCompany(companyRepository.getById(companyId));
        return jetSkiDetailsRepository.save(jetSkiDetails);
    }

    @Override
    @Transactional
    public JetSkiDetails updateJetSkiDetails(Integer companyId, JetSkiDetails jetSkiDetails) {
        JetSkiDetails jetSkiDetailsToUpdate = findJetSkiDetailsByCompanyId(companyId);
        jetSkiDetailsToUpdate.update(jetSkiDetails);
        return jetSkiDetailsRepository.save(jetSkiDetailsToUpdate);
    }

    @Override
    @Transactional
    public void deleteJetSkiDetails(Integer companyId) {
        jetSkiDetailsRepository.deleteByCompany_CompanyId(companyId);
    }

    @Override
    @Transactional
    public void updateSchedule(Integer companyId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkiesToSchedule) {
        JetSkiDetails jetSkiDetails = findJetSkiDetailsByCompanyId(companyId);

        integerScheduler.setScheduleMap(jetSkiDetails.getScheduleMap());
        integerScheduler.setTotalAvailable(jetSkiDetails.getTotalNumberOfJetSkies());
        integerScheduler.updateSchedule(day, startTime, endTime, numOfJetSkiesToSchedule);

        jetSkiDetailsRepository.save(jetSkiDetails);
    }

    @Override
    public double getSessionPrice(Integer companyId){
        JetSkiDetails jetSkiDetails = findJetSkiDetailsByCompanyId(companyId);
        return jetSkiDetails.getSessionPrice();
    }

}
