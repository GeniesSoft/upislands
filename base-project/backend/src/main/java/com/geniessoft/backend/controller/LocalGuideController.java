package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.*;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.LocalGuideSession;
import com.geniessoft.backend.service.AnalysisService;
import com.geniessoft.backend.service.LocalGuideService;
import com.geniessoft.backend.service.ScheduleService;
import com.geniessoft.backend.utility.customvalidator.ContentConstraints;
import com.geniessoft.backend.utility.mapper.LocalGuideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/local-guide")
@RestController
public class LocalGuideController {

    private final LocalGuideService localGuideService;
    private final LocalGuideMapper localGuideMapper;
    private final AnalysisService analysisService;
    private final ScheduleService scheduleService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String addLocalGuide(@Valid @RequestBody LocalGuideBaseDto localGuideBaseDto){
        localGuideService.saveLocalGuide(localGuideBaseDto);
        return "Local guide is saved.";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<LocalGuideGetDto> getAllLocalGuides() {
        return localGuideService.findAllLocalGuides().stream().map(localGuideMapper::localGuideToLocalGuideGetDto).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String updateLocalGuide(@Valid @RequestBody LocalGuideUpdateDto localGuideUpdateDto){
        localGuideService.updateLocalGuide(localGuideUpdateDto);
        return "Local guide is updated.";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteLocalGuide(@PathVariable(value = "id") Integer localGuideId){
        localGuideService.DeleteLocalGuide(localGuideId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Local guide is deleted.");
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value = "/{id}")
//    public LocalGuideGetDto getLocalGuide(@PathVariable(value = "id") Integer localGuideId){
//        LocalGuide localGuide = localGuideService.findLocalGuideById(localGuideId);
//        return getLocalGuideBaseDto(localGuide);
//    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value = "/mostBooked")
//    public LocalGuideBaseDto getMostBookedLocalGuide(){
//        LocalGuide localGuide = analysisService.findMostBookedLocalGuide();
//        return getLocalGuideBaseDto(localGuide);
//    }

//    private LocalGuideGetDto getLocalGuideBaseDto(LocalGuide localGuide) {
//        Company company= localGuide.getCompany();
//        Map<LocalGuideSession, Boolean> localGuideSessionBooleanMap = localGuide.getScheduleMap();
//        List<LocalDate> days = new ArrayList<>();
//        List<LocalTime> startTimes = new ArrayList<>();
//        List<Boolean> isScheduledList = new ArrayList<>();
//
//
//            for (Map.Entry<LocalGuideSession,Boolean> entry:localGuideSessionBooleanMap.entrySet()) {
//                LocalDate day = entry.getKey().getDay();
//                LocalTime startTime = entry.getKey().getStartTime();
//                Boolean isScheduled = entry.getValue();
//                days.add(day);
//                startTimes.add(startTime);
//                isScheduledList.add(isScheduled);
//            }
//
//        LocalGuideGetDto localGuideBaseDto= localGuideMapper.localGuideToLocalGuideGetDto(localGuide, company, days, startTimes, isScheduledList);
//        return localGuideBaseDto;
//    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value = "/bookedGuides")
//    public List<LocalGuideGetDto> getBookedLocalGuideByOrder(){
//        List<LocalGuide> localGuides = analysisService.findLocalGuidesByBookingDescOrder();
//        List<LocalGuideGetDto> localGuideBaseDtoList = new ArrayList<>();
//        List<LocalDate> days = null;
//        List<LocalTime> startTimes = null;
//        List<Boolean> isScheduledList = null;
//        for (LocalGuide localGuide: localGuides) {
//            Company company = localGuide.getCompany();
//            Map<LocalGuideSession, Boolean> localGuideSessionBooleanMap = localGuide.getScheduleMap();
//            System.out.println(localGuideSessionBooleanMap.isEmpty());
//            if (!localGuideSessionBooleanMap.isEmpty()){
//                days = new ArrayList<>();
//                startTimes = new ArrayList<>();
//                isScheduledList = new ArrayList<>();
//            for (Map.Entry<LocalGuideSession,Boolean> entry:localGuideSessionBooleanMap.entrySet()) {
//               LocalDate day = entry.getKey().getDay();
//               LocalTime startTime = entry.getKey().getStartTime();
//               Boolean isScheduled = entry.getValue();
//               days.add(day);
//               startTimes.add(startTime);
//               isScheduledList.add(isScheduled);
//            }}
//            LocalGuideGetDto localGuideGetDto = localGuideMapper.localGuideToLocalGuideGetDto(localGuide,company, days, startTimes, isScheduledList);
//            localGuideBaseDtoList.add(localGuideGetDto);
//        }
//        return localGuideBaseDtoList;
//    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/GuidesAverage")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Map<Integer,Double> getLocalGuidesByAverageOrder(){
        Map<LocalGuide,Double> localGuideAverageMap = analysisService.findLocalGuidesByRatingDescOrder();
        Map<Integer,Double> localGuideIdAverageMap = new HashMap<>();
       for (Map.Entry<LocalGuide,Double> entry : localGuideAverageMap.entrySet() ){
           LocalGuide localGuide = entry.getKey();
           localGuideIdAverageMap.put(localGuide.getLocalGuideId(),entry.getValue());
       }
        return localGuideIdAverageMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/available-local-guides")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Map<Integer,Integer> getAvailableLocalGuides(@RequestParam(value = "locationId") Integer locationId,@RequestParam(value = "day") String day,
            @RequestParam(value = "startTime") String startTime,@RequestParam(value = "endTime") String endTime){
        //List<Integer> localGuideIds = new ArrayList<>();
        Map<Integer,Integer> localGuideIdJetSkiMap = new HashMap<>();
        List<LocalGuide> localGuides = scheduleService.getAvailableLocalGuides(locationId,LocalDate.parse(day),LocalTime.parse(startTime), LocalTime.parse(endTime));
        for (LocalGuide localGuide: localGuides) {
           // localGuideIds.add(localGuide.getLocalGuideId());
            Integer availableJetSkiCount = scheduleService.getAvailableJetSkiCount(localGuide,LocalDate.parse(day),LocalTime.parse(startTime), LocalTime.parse(endTime));
            localGuideIdJetSkiMap.put(localGuide.getLocalGuideId(),availableJetSkiCount);
        }

        return localGuideIdJetSkiMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(
            path = "/{localGuideId}/content",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String addLocalguideContent(@PathVariable("localGuideId") int localGuideId, @RequestParam("file") @ContentConstraints MultipartFile file, @RequestParam("contentText") String content_text) {

        localGuideService.addLocalGuideContent(localGuideId ,file, content_text);
        return "Location content is uploaded.";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/frontend")
    public List<LocalGuideGetFrontendDto> localGuideGetFrontend(){
        return localGuideService.getFrontendDtoList();
    }
}
