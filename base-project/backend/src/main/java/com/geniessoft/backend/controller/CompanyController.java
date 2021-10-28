package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.CompanyRegisterDto;
import com.geniessoft.backend.dto.CompanyUpdateDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.service.CompanyService;
import com.geniessoft.backend.utility.customvalidator.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper mapper;

    @GetMapping
    public ResponseEntity<CompanyRegisterDto> getCompany
            (@RequestParam(value = "companyId") Integer companyId){

        Company company = companyService.findCompanyById(companyId);
        Address address = company.getCompanyAddress();
        CompanyRegisterDto dto = mapper.companyToCompanyDto(company, address);
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
                .body("Save is successful");
    }

    @PutMapping
    public ResponseEntity<String> updateCompany
            (@Valid @RequestBody CompanyUpdateDto companyUpdateDto){
        companyService.updateCompany(companyUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Update operation is successful");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompany
            (@RequestParam(value = "companyId") Integer companyId){
        companyService.deleteCompany(companyId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Delete operation is successful");
    }
}
