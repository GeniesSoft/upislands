package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.CompanyRegisterDto;
import com.geniessoft.backend.dto.CompanyUpdateDto;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.repository.CompanyRepository;
import com.geniessoft.backend.service.*;
import com.geniessoft.backend.utility.bucket.BucketName;
import com.geniessoft.backend.utility.bucket.FolderNames;
import com.geniessoft.backend.utility.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;
    private final AddressService addressService;
    private final UserService userService;
    private final LocationService locationService;
    private final ContentService contentService;
    private final FileStoreService fileStoreService;
    private final CompanyContentService companyContentService;

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
        checkUserHasCompany(companyRegisterDto.getUserId());
        Company company = mapper.companyRegisterDtoToCompany(companyRegisterDto);
        Address address = mapper.companyRegisterDtoToAddress(companyRegisterDto);
        addressService.saveAddress(address);
        User user = userService.findUser(companyRegisterDto.getUserId());
        company.setCompanyAddress(address);
        company.setJobOwner(user);
        List<Location> locationList = getLocations(companyRegisterDto.getLocationIdList());
        company.setLocationList(locationList);
        companyRepository.save(company);

        return company;
    }

    private List<Location> getLocations(List<Integer> locationIdList) {
        List<Location> locationList = new ArrayList<>();
        for(Integer value : locationIdList){
            Location location = locationService.findLocationById(value);
            locationList.add(location);
        }
        return locationList;
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
    public void checkUserHasCompany(int userId) {
        Optional<Company> company = companyRepository
                .findFirstByJobOwnerUserId(userId);
        if(company.isEmpty()){
            return;
        }
        else {
            throw new EntityExistsException("This user already has a company.");
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
        mapper.updateCompany(company, companyUpdateDto);
        mapper.updateAddress(company.getCompanyAddress(), companyUpdateDto);
        company.setLocationList(getLocations(companyUpdateDto.getLocationIdList()));
        companyRepository.save(company);

        return company;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addCompanyProfileImage(int companyId, MultipartFile file) {

        // If there is company profile image then previous image deleted.
        Company company = findCompanyById(companyId);
        if(company.getCompanyProfileImage() != null){
            deleteCompanyProfileImage(company);
        }

        Map<String,String> metadata = fileStoreService.getMetadata(file);
        String path = String.format("%s/%s", BucketName.BUCKET_NAME.getBucketName(), FolderNames.company_profile_images);
        String fileName = String.format("%s-%s", company.getCompanyId(), file.getOriginalFilename());

        try {

            Content companyProfileImage = contentService.saveContent(fileName,path,file.getContentType());
            company.setCompanyProfileImage(companyProfileImage);
            fileStoreService.upload(path,fileName,Optional.of(metadata),file.getInputStream());
            System.out.println("Company profile image is uploaded");
        }
        catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void deleteCompanyProfileImage(Company company) {
        Content content = company.getCompanyProfileImage();
        contentService.deleteContent(content.getContentId());
        fileStoreService.delete(content.getContentPath(),content.getContentName());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addCompanyContent(int companyId, MultipartFile file, String content_text) {

        Company company = findCompanyById(companyId);
        Map<String,String> metadata = fileStoreService.getMetadata(file);

        String path = String.format("%s/%s",
                BucketName.BUCKET_NAME.getBucketName(),
                FolderNames.company_contents +"/"+ company.getCompanyId());

        String fileName = String.format("%s-%s",
                System.currentTimeMillis(),
                file.getOriginalFilename());

        try {

            companyContentService.saveCompanyContent
                    (fileName,path,file.getContentType(),content_text,company);
            fileStoreService.upload(path,fileName,Optional.of(metadata),file.getInputStream());
            System.out.println("Company content is uploaded");
        }
        catch (IOException e){
            throw new IllegalStateException(e);
        }
    }
}
