package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.CompanyRegisterDto;
import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.service.CompanyService;
import com.geniessoft.backend.utility.customvalidator.SimpleSourceDestinationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final SimpleSourceDestinationMapper mapper;

    @GetMapping
    public ResponseEntity<CompanyRegisterDto> getCompany
            (@RequestParam(value = "companyId") Integer companyId){

        Company company = companyService.findCompanyById(companyId);
        Address address = company.getCompanyAddress();
        CompanyRegisterDto dto = SimpleSourceDestinationMapper.INSTANCE.companyToCompanyDto(company, address);
        dto.setUserId(company.getJobOwner().getUserId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @PostMapping(value = "register")
    public ResponseEntity<String> getCompany
            (@RequestBody CompanyRegisterDto companyRegisterDto){

        /*Company company = mapper.companyDtoToCompany(companyRegisterDto);
        Address address = mapper.compnayDtoToAddress(companyRegisterDto);
        System.out.println("Company : "+company);
        System.out.println("Address : "+address);*/

        return null;
    }
}
