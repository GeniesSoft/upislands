package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.repository.LocalGuideRepository;
import com.geniessoft.backend.service.CompanyService;
import com.geniessoft.backend.service.LocalGuideService;
import com.geniessoft.backend.utility.mapper.LocalGuideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LocalGuideServiceImpl implements LocalGuideService {
    CompanyService companyService;
    LocalGuideMapper localGuideMapper;
    LocalGuideRepository localGuideRepository;

    @Override
    public LocalGuide findLocalGuideById(int localGuideId) {
        Optional<LocalGuide> localGuide = localGuideRepository.findLocalGuideByLocalGuideId(localGuideId);
        return localGuide.orElseThrow(() -> new EntityNotFoundException("Local Guide is not found."));
    }

    @Transactional
    @Override
    public LocalGuide updateLocalGuide(LocalGuideUpdateDto localGuideUpdateDto) {
        LocalGuide localGuide = findLocalGuideById(localGuideUpdateDto.getLocalGuideId());
        localGuideMapper.updateLocalGuide(localGuide, localGuideUpdateDto);
        return localGuideRepository.save(localGuide);
    }

    @Transactional
    @Override
    public LocalGuide DeleteLocalGuide(int localGuideId) {
        localGuideRepository.deleteById(localGuideId);
        return findLocalGuideById(localGuideId);
    }

    @Transactional
    @Override
    public LocalGuide saveLocalGuide(LocalGuideBaseDto localGuideBaseDto) {
        Company company = companyService.findCompanyById(localGuideBaseDto.getCompanyID());
        LocalGuide localGuide = localGuideMapper.localGuideBaseDtoToLocalGuide(localGuideBaseDto);
        localGuide.setCompany(company);

        return localGuideRepository.save(localGuide);
    }
}
