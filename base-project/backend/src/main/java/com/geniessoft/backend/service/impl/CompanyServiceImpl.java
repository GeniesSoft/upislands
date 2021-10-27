package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.CompanyRegisterDto;
import com.geniessoft.backend.dto.CompanyUpdateDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.repository.CompanyRepository;
import com.geniessoft.backend.service.AddressService;
import com.geniessoft.backend.service.CompanyService;
import com.geniessoft.backend.service.UserService;
import com.geniessoft.backend.utility.customvalidator.SimpleSourceDestinationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final SimpleSourceDestinationMapper mapper;
    private final AddressService addressService;
    private final UserService userService;

    @Override
    public Company findCompanyById(Integer companyId) {
        Optional<Company> company = companyRepository
                .findByCompanyIdAndDeletedIsFalse(companyId);
        return company.orElseThrow(() -> new EntityNotFoundException("Company is not found."));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Company saveCompany(CompanyRegisterDto companyRegisterDto) {

        checkCompanyName(companyRegisterDto.getCompanyName());
        Company company = mapper.companyDtoToCompany(companyRegisterDto);
        Address address = mapper.companyDtoToAddress(companyRegisterDto);
        addressService.saveAddress(address);
        User user = userService.findUser(companyRegisterDto.getUserId());
        company.setCompanyAddress(address);
        company.setJobOwner(user);
        companyRepository.save(company);

        return company;
    }

    @Override
    public void deleteCompany(Integer companyId) {
        Company company = findCompanyById(companyId);
        company.setDeleted(true);
        companyRepository.save(company);
    }

    @Override
    public void checkCompanyName(String name) {
        Optional<Company> company = companyRepository
                .findFirstByCompanyNameEquals(name.trim());
        if(company.isEmpty()){
            return;
        }
        else {
            throw new EntityExistsException("This company name is used");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Company updateCompany(CompanyUpdateDto companyUpdateDto) {

        Company company = findCompanyById(companyUpdateDto.getCompanyId());
        if(!company.getCompanyName()
                .equals(companyUpdateDto.getCompanyName())){
            checkCompanyName(companyUpdateDto.getCompanyName());
        }
        mapper.updateCompany(company,companyUpdateDto);
        mapper.updateAddress(company.getCompanyAddress(),companyUpdateDto);
        companyRepository.save(company);

        return company;
    }
}