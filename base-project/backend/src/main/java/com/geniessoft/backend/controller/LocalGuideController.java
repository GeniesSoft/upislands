package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.service.LocalGuideService;
import com.geniessoft.backend.utility.mapper.LocalGuideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/local-guide")
@RestController
public class LocalGuideController {

    private final LocalGuideService localGuideService;
    private final LocalGuideMapper localGuideMapper;

    @PutMapping(value = "/save")
    public ResponseEntity<String> addLocalGuide(@Valid @RequestBody LocalGuideBaseDto localGuideBaseDto){
        localGuideService.saveLocalGuide(localGuideBaseDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Local guide is saved.");
    }

    @PostMapping
    public ResponseEntity<String> updateLocalGuide(@Valid @RequestBody LocalGuideUpdateDto localGuideUpdateDto){
        localGuideService.updateLocalGuide(localGuideUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Local guide is updated.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLocalGuide(@RequestParam(value = "localGuideId") Integer localGuideId){
        localGuideService.DeleteLocalGuide(localGuideId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Local guide is deleted.");
    }

    @GetMapping
    public ResponseEntity<LocalGuideBaseDto> getLocalGuide(@RequestParam(value = "localGuideId") Integer localGuideId){
        LocalGuide localGuide = localGuideService.findLocalGuideById(localGuideId);
        Company company = localGuide.getCompany();
        LocalGuideBaseDto localGuideBaseDto  = localGuideMapper.localGuideToLocalGuideDto(localGuide,company);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(localGuideBaseDto);
    }



}
