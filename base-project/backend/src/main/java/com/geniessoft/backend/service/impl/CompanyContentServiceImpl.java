package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.CompanyContent;
import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.repository.CompanyContentRepository;
import com.geniessoft.backend.service.CompanyContentService;
import com.geniessoft.backend.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyContentServiceImpl implements CompanyContentService {

    private final ContentService contentService;
    private final CompanyContentRepository companyContentRepository;

    @Override
    public CompanyContent saveCompanyContent(
            String fileName, String path, String contentType,
            String contentText, Company company) {

        Content content = contentService.saveContent(fileName,path,contentType);

        CompanyContent companyContent = new CompanyContent();
        companyContent.setContent(content);
        companyContent.setCompany(company);
        companyContent.setContent_text(contentText);

        return companyContentRepository.save(companyContent);
    }
}
