package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.JetSkiDetails;
import com.geniessoft.backend.repository.CompanyRepository;
import com.geniessoft.backend.repository.JetSkiDetailsRepository;
import com.geniessoft.backend.utility.schedule.JetSkiScheduler;
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

    private final JetSkiDetailsRepository jetSkiDetailsRepository;
    private final CompanyRepository companyRepository;
    private final JetSkiScheduler jetSkiScheduler;

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
        System.out.println(jetSkiDetails.getJetSkiDetailsId());
        JetSkiDetails jetSkiDetails1 = jetSkiDetailsRepository.save(jetSkiDetailsToUpdate);
        return jetSkiDetails1;
    }

    @Override
    @Transactional
    public void deleteJetSkiDetails(Integer companyId) {
        jetSkiDetailsRepository.deleteByCompany_CompanyId(companyId);
    }

    @Override
    @Transactional
    public void updateSchedule(JetSkiDetails jetSkiDetails) {
        jetSkiDetailsRepository.save(jetSkiDetails);
    }

    @Override
    public double getSessionPrice(Integer companyId){
        JetSkiDetails jetSkiDetails = findJetSkiDetailsByCompanyId(companyId);
        return jetSkiDetails.getSessionPrice();
    }

}
