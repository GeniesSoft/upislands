package com.geniessoft.backend.utility.customvalidator;

import com.geniessoft.backend.dto.*;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface SimpleSourceDestinationMapper {

    CompanyRegisterDto companyToCompanyDto(Company company, Address address);

    Company companyDtoToCompany(CompanyRegisterDto dto);

    Address companyDtoToAddress(CompanyRegisterDto dto);

    User userRegDtoToUser(UserRegisterDto dto);

    UserUpdateDto userToUserUpdateDto(User user);

    void updateAddress(@MappingTarget Address address, CompanyUpdateDto dto);

    void updateCompany(@MappingTarget Company company, CompanyUpdateDto dto);

    void updateUser(@MappingTarget User user, UserUpdateDto dto);
}
