package com.geniessoft.backend.service;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.CompanyContent;

public interface CompanyContentService {

    CompanyContent saveCompanyContent(
            String fileName,
            String path,
            String contentType,
            String contentText,
            Company company);
}
