package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.CompanyGetDto;
import com.geniessoft.backend.dto.CompanyRegisterDto;
import com.geniessoft.backend.dto.CompanyUpdateDto;
import com.geniessoft.backend.dto.LocationGetDto;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Validated
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper mapper;
    private final LocationMapper locationMapper;

    @GetMapping
    public ResponseEntity<CompanyGetDto> getCompany
            (@RequestParam(value = "companyId") Integer companyId){

        Company company = companyService.findCompanyById(companyId);
        Address address = company.getCompanyAddress();

        List<Location> locationList = company.getLocationList();
        List<LocationGetDto> locationGetDtoList = locationList
                .stream()
                .map((e)-> locationMapper
                            .locationToLocationGetDto(e,e.getAddress()))
                            .collect(Collectors.toList());

        CompanyGetDto dto = mapper.companyToCompanyGetDto(company, address, locationGetDtoList);
        dto.setUserId(company.getJobOwner().getUserId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @PostMapping(value = "save")
    public ResponseEntity<String> addCompany
            (@Validated @RequestBody CompanyRegisterDto companyRegisterDto){
        companyService.saveCompany(companyRegisterDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Company is saved");
    }

    @PutMapping
    public ResponseEntity<String> updateCompany
            (@Valid @RequestBody CompanyUpdateDto companyUpdateGetDto){
        companyService.updateCompany(companyUpdateGetDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Company is updated.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompany
            (@RequestParam(value = "companyId") Integer companyId){
        companyService.deleteCompany(companyId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Delete operation is successful");
    }

    @PostMapping(
            path = "{companyId}/image/upload",
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
            path = "{companyId}/content/upload",
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
}
