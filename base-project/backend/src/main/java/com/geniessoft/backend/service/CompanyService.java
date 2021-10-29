package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.CompanyRegisterDto;
import com.geniessoft.backend.dto.CompanyUpdateDto;
import com.geniessoft.backend.model.Company;
import org.springframework.web.multipart.MultipartFile;

public interface CompanyService {

    Company findCompanyById(Integer companyId);

    Company saveCompany(CompanyRegisterDto companyRegisterDto);

    Company updateCompany(CompanyUpdateDto companyUpdateGetDto);

    void checkUserHasCompany(int userId);

    void deleteCompany(Integer companyId);

    void checkCompanyName(String name);

    void deleteCompanyProfileImage(Company company);

    void addCompanyProfileImage(int companyId, MultipartFile file);

    void addCompanyContent(int companyId, MultipartFile file, String content_text);
}
