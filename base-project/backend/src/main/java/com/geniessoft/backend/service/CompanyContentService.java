package com.geniessoft.backend.service;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.CompanyContent;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyContentService {

    CompanyContent saveCompanyContent(
            String fileName,
            String path,
            String contentType,
            String contentText,
            Company company);

    CompanyContent getCompanyContent(int companyContentId);

    List<CompanyContent> getCompanyContentPage(int companyId, int offset, int pageSize);

    void deleteCompanyContent(int companyContentId);

    void updateCompanyContent(int companyContentId, String content_text);
}
