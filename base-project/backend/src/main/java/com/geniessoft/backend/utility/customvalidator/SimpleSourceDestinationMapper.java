package com.geniessoft.backend.utility.customvalidator;

import com.geniessoft.backend.dto.CompanyRegisterDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface SimpleSourceDestinationMapper {

    SimpleSourceDestinationMapper INSTANCE = Mappers.getMapper(SimpleSourceDestinationMapper.class);
    CompanyRegisterDto companyToCompanyDto(Company company, Address address);
    Company companyDtoToCompany(CompanyRegisterDto companyRegisterDto);
    Address compnayDtoToAddress(CompanyRegisterDto companyRegisterDto);
}
