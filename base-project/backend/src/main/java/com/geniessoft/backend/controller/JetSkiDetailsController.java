package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.JetSkiDetailsBaseDto;
import com.geniessoft.backend.dto.JetSkiDetailsGetDto;
import com.geniessoft.backend.dto.JetSkiDetailsSaveDto;
import com.geniessoft.backend.dto.JetSkiDetailsUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.JetSkiDetails;
import com.geniessoft.backend.service.CompanyService;
import com.geniessoft.backend.service.JetSkiDetailsService;
import com.geniessoft.backend.utility.mapper.JetSkiDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/jet-ski-details")
@RestController
public class JetSkiDetailsController {

    private final JetSkiDetailsService jetSkiDetailsService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{companyId}")
    public JetSkiDetailsGetDto getJetSkiDetails(
            @PathVariable(value = "companyId") Integer companyId) {
        JetSkiDetails jetSkiDetails = jetSkiDetailsService.findJetSkiDetailsByCompanyId(companyId);
        return JetSkiDetailsMapper.INSTANCE.jetSkiDetailsToJetSkiDetailsGetDto(jetSkiDetails);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{companyId}")
    public String saveJetSkiDetails(
            @PathVariable(value = "companyId") Integer companyId,
            @Valid @RequestBody JetSkiDetailsSaveDto jetSkiDetailsSaveDto) {
        jetSkiDetailsService.saveJetSkiDetails(
                companyId,
                JetSkiDetailsMapper.INSTANCE.jetSkiDetailsSaveDtoToJetSkiDetails(jetSkiDetailsSaveDto)
        );
        return "Jet ski details saved";
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{companyId}")
    public String updateJetSkiDetails(
            @PathVariable(value = "companyId") Integer companyId,
            @Valid @RequestBody JetSkiDetailsUpdateDto jetSkiDetailsUpdateDto) {
        jetSkiDetailsService.updateJetSkiDetails(
                companyId,
                JetSkiDetailsMapper.INSTANCE.jetSkiDetailsUpdateDtoToJetSkiDetails(jetSkiDetailsUpdateDto)
        );
        return "Jet ski details updated";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{companyId}")
    public String deleteJetSkiDetails(
            @PathVariable(value = "companyId") Integer companyId) {
        jetSkiDetailsService.deleteJetSkiDetails(companyId);
        return "Jet ski details deleted";
    }

}
