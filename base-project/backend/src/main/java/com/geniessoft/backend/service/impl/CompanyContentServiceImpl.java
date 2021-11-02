package com.geniessoft.backend.service.impl;

import com.amazonaws.services.workdocs.model.EntityNotExistsException;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.CompanyContent;
import com.geniessoft.backend.model.Content;
import com.geniessoft.backend.repository.CompanyContentRepository;
import com.geniessoft.backend.service.CompanyContentService;
import com.geniessoft.backend.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyContentServiceImpl implements CompanyContentService {

    private final ContentService contentService;
    private final CompanyContentRepository companyContentRepository;
    private final FileStoreService fileStoreService;

    @Override
    public CompanyContent saveCompanyContent(
            String fileName, String path, String contentType,
            String contentText, Company company) {

        Content content = contentService.saveContent(fileName,path,contentType);

        CompanyContent companyContent = new CompanyContent();
        companyContent.setContent(content);
        companyContent.setCompany(company);
        companyContent.setContentText(contentText);

        return companyContentRepository.save(companyContent);
    }

    @Override
    public void deleteCompanyContent(int companyContentId) {
        CompanyContent companyContent = getCompanyContent(companyContentId);
        companyContentRepository.deleteById(companyContentId);
        fileStoreService.delete(
                companyContent.getContent().getContentPath(),
                companyContent.getContent().getContentName());
    }

    @Override
    public CompanyContent getCompanyContent(int companyContentId) {
        Optional<CompanyContent> companyContent = companyContentRepository.findById(companyContentId);
        if(companyContent.isEmpty()){
            throw new EntityNotFoundException("Company content is not found.");
        }
        return companyContent.get();
    }

    @Override
    public void updateCompanyContent(int companyContentId, String content_text) {
        CompanyContent companyContent = getCompanyContent(companyContentId);
        companyContent.setContentText(content_text);
        companyContentRepository.save(companyContent);
    }

    @Override
    public List<CompanyContent> getCompanyContentPage(int companyId, int offset, int pageSize) {

        Page<CompanyContent> companyContentPage =
                companyContentRepository.findAll(PageRequest.of(offset,pageSize));

        return companyContentPage.toList();
    }
}
