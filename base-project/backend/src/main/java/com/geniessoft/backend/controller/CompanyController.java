package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.*;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.service.CompanyService;
import com.geniessoft.backend.utility.customvalidator.ContentConstraints;
import com.geniessoft.backend.utility.customvalidator.ImageConstraint;
import com.geniessoft.backend.utility.mapper.CompanyMapper;
import com.geniessoft.backend.utility.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/company")
@RequiredArgsConstructor
@Validated
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper mapper;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public String addCompany(
            @Validated @RequestBody CompanyRegisterDto companyRegisterDto){
        companyService.saveCompany(companyRegisterDto);
        return "Company successfully saved";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public CompanyGetDto getCompany(
            @PathVariable(value = "id") Integer id){

        Company company = companyService.findCompanyById(id);
        Address address = company.getAddress();
        List<Location> locationList = company.getLocationList();

        List<LocationGetDto> locationGetDtoList = locationList
                .stream()
                .map((location)-> LocationMapper.INSTANCE
                            .locationToLocationGetDto(location, location.getAddress()))
                            .collect(Collectors.toList());

        CompanyGetDto dto = mapper.companyToCompanyGetDto(company, address, locationGetDtoList);

        dto.setUserId(company.getJobOwner().getUserId());

        return dto;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public String updateCompany(
            @Valid @RequestBody CompanyUpdateDto companyUpdateGetDto){
        companyService.updateCompany(companyUpdateGetDto);
        return "Company successfully updated";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public String deleteCompany(
            @PathVariable(value = "id") Integer id){
        companyService.deleteCompany(id);
        return "Company successfully deleted";
    }

    @PostMapping(
            path = "{companyId}/profileImage",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> addCompanyProfileImage(
            @PathVariable("companyId") int companyId,
            @RequestParam("file") @ImageConstraint MultipartFile file){
        companyService.addCompanyProfileImage(companyId ,file);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Company profile image is uploaded.");
    }

    @PostMapping(
            path = "{companyId}/content",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> addCompanyContent(
            @PathVariable("companyId") int companyId,
            @RequestParam("file") @ContentConstraints MultipartFile file,
            @RequestParam("content_text") String content_text){
        companyService.addCompanyContent(companyId ,file, content_text);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Company content is uploaded.");
    }

    @DeleteMapping(value = "/content")
    public ResponseEntity<String> deleteCompanyContent
            (@RequestParam(value = "companyContentId") Integer companyContentId){
        companyService.deleteCompanyContent(companyContentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Content is successfully deleted.");
    }

    @PutMapping(value = "/content")
    public ResponseEntity<String> updateCompanyContent
            (@RequestParam(value = "companyContentId") Integer companyContentId,
             @RequestParam(value = "contentText") String contentText){
        companyService.updateCompanyContent(companyContentId,contentText);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Company content is successfully updated.");
    }

    @GetMapping(value = "/contents/{companyId}/{offset}/{pageSize}")
    public ResponseEntity<List<ContentDto>> getCompanyContentList(
            @PathVariable("companyId") int companyId,
            @PathVariable("offset") int offset,
            @PathVariable("pageSize") int pageSize){

        List<ContentDto> contentDtoList = companyService.
                getCompanyContents(companyId, offset, pageSize);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contentDtoList);
    }

    @GetMapping(value = "/{companyId}/profileImage")
    public ResponseEntity<ProfileImageDto> getProfileImage(
            @PathVariable("companyId") int companyId){

        byte[] image = companyService.getCompanyProfileImage(companyId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ProfileImageDto(image));
    }
}
