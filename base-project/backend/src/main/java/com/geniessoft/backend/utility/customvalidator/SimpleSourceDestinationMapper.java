package com.geniessoft.backend.utility.customvalidator;

import com.geniessoft.backend.dto.*;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel="spring")
public interface SimpleSourceDestinationMapper {

    //////////////////////////////--Company--//////////////////////////////////

    CompanyRegisterDto companyToCompanyDto(Company company, Address address);

    Company companyRegisterDtoToCompany(CompanyRegisterDto dto);

    Address companyRegisterDtoToAddress(CompanyRegisterDto dto);

    CompanyGetDto companyToCompanyGetDto(Company company, Address address, List<LocationGetDto> locationGetDtoList);

    void updateAddress(@MappingTarget Address address, CompanyUpdateDto dto);

    void updateCompany(@MappingTarget Company company, CompanyUpdateDto dto);

    //////////////////////////////--User--//////////////////////////////////

    void updateUser(@MappingTarget User user, UserUpdateDto dto);

    User userRegDtoToUser(UserRegisterDto dto);

    UserUpdateDto userToUserUpdateDto(User user);
}
