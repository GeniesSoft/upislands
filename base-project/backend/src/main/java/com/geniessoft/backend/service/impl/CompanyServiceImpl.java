package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.repository.CompanyRepository;
import com.geniessoft.backend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company findCompanyById(Integer companyId) {
        Optional<Company> company = companyRepository
                .findByCompanyIdAndDeletedIsFalse(companyId);
        return company.orElseThrow(() -> new EntityNotFoundException("Company is not found."));
    }
}
