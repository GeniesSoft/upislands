package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.CompanyRegisterDto;
import com.geniessoft.backend.dto.CompanyUpdateDto;
import com.geniessoft.backend.dto.ContentDto;
import com.geniessoft.backend.model.Company;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyService {

    List<Company> findAllCompanies();

    Company findCompanyById(Integer companyId);

    Company saveCompany(CompanyRegisterDto companyRegisterDto);

    Company updateCompany(CompanyUpdateDto companyUpdateGetDto);

    List<Company> findCompaniesByLocationId(Integer locationId);

    List<ContentDto> getCompanyContents(int companyId, int offset, int pageSize);

    void addLocation(Integer companyId, Integer locationId);

    byte[] getCompanyProfileImage(int companyId);

    void checkUserHasCompany(long userId);

    void deleteCompany(Integer companyId);

    void checkCompanyName(String name);

    void deleteCompanyProfileImage(Company company);

    void addCompanyProfileImage(int companyId, MultipartFile file);

    void addCompanyContent(int companyId, MultipartFile file, String content_text);

    void deleteCompanyContent(Integer companyContentId);

    void updateCompanyContent(Integer companyContentId, String contentText);
}
