package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.service.AnalysisService;
import com.geniessoft.backend.service.LocalGuideService;
import com.geniessoft.backend.utility.mapper.LocalGuideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/local-guide")
@RestController
public class LocalGuideController {

    private final LocalGuideService localGuideService;
    private final LocalGuideMapper localGuideMapper;
    private final AnalysisService analysisService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public String addLocalGuide(@Valid @RequestBody LocalGuideBaseDto localGuideBaseDto){
        localGuideService.saveLocalGuide(localGuideBaseDto);
        return "Local guide is saved.";
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public String updateLocalGuide(@Valid @RequestBody LocalGuideUpdateDto localGuideUpdateDto){
        localGuideService.updateLocalGuide(localGuideUpdateDto);
        return "Local guide is updated.";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteLocalGuide(@PathVariable(value = "id") Integer localGuideId){
        localGuideService.DeleteLocalGuide(localGuideId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Local guide is deleted.");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public LocalGuideBaseDto getLocalGuide(@PathVariable(value = "id") Integer localGuideId){
        LocalGuide localGuide = localGuideService.findLocalGuideById(localGuideId);
        Company company = localGuide.getCompany();
        LocalGuideBaseDto localGuideBaseDto  = localGuideMapper.localGuideToLocalGuideDto(localGuide,company);
        return localGuideBaseDto;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/mostBooked")
    public LocalGuideBaseDto getMostBookedLocalGuide(){
        LocalGuide localGuide = analysisService.findMostBookedLocalGuide();
        Company company= localGuide.getCompany();
        LocalGuideBaseDto localGuideBaseDto= localGuideMapper.localGuideToLocalGuideDto(localGuide, company);
        return localGuideBaseDto;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bookedGuides")
    public List<LocalGuideBaseDto> getBookedLocalGuideByOrder(){
        List<LocalGuide> localGuides = analysisService.findLocalGuidesByBookingDescOrder();
        List<LocalGuideBaseDto> localGuideBaseDtoList = new ArrayList<>();
        for (LocalGuide localGuide: localGuides) {
            Company company = localGuide.getCompany();
            LocalGuideBaseDto localGuideBaseDto = localGuideMapper.localGuideToLocalGuideDto(localGuide,company);
            localGuideBaseDtoList.add(localGuideBaseDto);
        }
        return localGuideBaseDtoList;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/GuidesAverage")
    public Map<Integer,Double> getLocalGuidesByAverageOrder(){
        Map<LocalGuide,Double> localGuideAverageMap = analysisService.findLocalGuidesByRatingDescOrder();
        Map<Integer,Double> localGuideIdAverageMap = new HashMap<>();
       for (Map.Entry<LocalGuide,Double> entry : localGuideAverageMap.entrySet() ){
           LocalGuide localGuide = entry.getKey();
           localGuideIdAverageMap.put(localGuide.getLocalGuideId(),entry.getValue());
       }
        return localGuideIdAverageMap;
    }



}
