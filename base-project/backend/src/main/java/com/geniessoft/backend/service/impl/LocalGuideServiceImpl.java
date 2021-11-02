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
    private final CompanyService companyService;
    private final LocalGuideMapper localGuideMapper;
    private final LocalGuideRepository localGuideRepository;

    @Override
    public LocalGuide findLocalGuideById(int localGuideId) {
        Optional<LocalGuide> localGuide = localGuideRepository.findByLocalGuideId(localGuideId);
        return localGuide.orElseThrow(() -> new EntityNotFoundException("Local Guide is not found."));
    }

    @Transactional
    @Override
    public LocalGuide updateLocalGuide(LocalGuideUpdateDto localGuideUpdateDto) {
        LocalGuide localGuide = findLocalGuideById(localGuideUpdateDto.getLocalGuideId());
        localGuide.setLocalGuideName(localGuide.getLocalGuideName());
        localGuideMapper.updateLocalGuide(localGuide, localGuideUpdateDto);
        return localGuideRepository.save(localGuide);
    }

    @Transactional
    @Override
    public LocalGuide DeleteLocalGuide(int localGuideId) {
        LocalGuide localGuide = findLocalGuideById(localGuideId);
        localGuideRepository.deleteLocalGuideByLocalGuideId(localGuideId);
        return  localGuide;
    }

    @Transactional
    @Override
    public LocalGuide saveLocalGuide(LocalGuideBaseDto localGuideBaseDto) {
        Company company = companyService.findCompanyById(localGuideBaseDto.getCompanyId());
        LocalGuide localGuide = localGuideMapper.localGuideBaseDtoToLocalGuide(localGuideBaseDto);
        localGuide.setCompany(company);
        localGuide.setLocalGuideName(localGuideBaseDto.getLocalGuideName());

        return localGuideRepository.save(localGuide);
    }
}
