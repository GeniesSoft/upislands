package com.geniessoft.backend.controller;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideGetDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.Booking;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.LocalGuideSession;
import com.geniessoft.backend.service.AnalysisService;
import com.geniessoft.backend.service.LocalGuideService;
import com.geniessoft.backend.service.ScheduleService;
import com.geniessoft.backend.utility.mapper.LocalGuideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private final ScheduleService scheduleService;

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
    public LocalGuideGetDto getLocalGuide(@PathVariable(value = "id") Integer localGuideId){
        LocalGuide localGuide = localGuideService.findLocalGuideById(localGuideId);
        return getLocalGuideBaseDto(localGuide);
    }
  /*  @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get-available")
    public LocalGuideSession getAvailableLocalGuide(){
        LocalGuideSession localGuideSession = scheduleService.getLocalGuideScheduleByLocalGuideId(0);
        return localGuideSession;
    }*/
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/mostBooked")
    public LocalGuideBaseDto getMostBookedLocalGuide(){
        LocalGuide localGuide = analysisService.findMostBookedLocalGuide();
        return getLocalGuideBaseDto(localGuide);
    }

    private LocalGuideGetDto getLocalGuideBaseDto(LocalGuide localGuide) {
        Company company= localGuide.getCompany();
        Map<LocalGuideSession, Boolean> localGuideSessionBooleanMap = localGuide.getScheduleMap();
        List<LocalDate> days = new ArrayList<>();
        List<LocalTime> startTimes = new ArrayList<>();
        List<Boolean> isScheduledList = new ArrayList<>();


            for (Map.Entry<LocalGuideSession,Boolean> entry:localGuideSessionBooleanMap.entrySet()) {
                LocalDate day = entry.getKey().getDay();
                LocalTime startTime = entry.getKey().getStartTime();
                Boolean isScheduled = entry.getValue();
                days.add(day);
                startTimes.add(startTime);
                isScheduledList.add(isScheduled);
            }

        LocalGuideGetDto localGuideBaseDto= localGuideMapper.localGuideToLocalGuideGetDto(localGuide, company, days, startTimes, isScheduledList);
        return localGuideBaseDto;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bookedGuides")
    public List<LocalGuideGetDto> getBookedLocalGuideByOrder(){
        List<LocalGuide> localGuides = analysisService.findLocalGuidesByBookingDescOrder();
        List<LocalGuideGetDto> localGuideBaseDtoList = new ArrayList<>();
        List<LocalDate> days = null;
        List<LocalTime> startTimes = null;
        List<Boolean> isScheduledList = null;
        for (LocalGuide localGuide: localGuides) {
            Company company = localGuide.getCompany();
            Map<LocalGuideSession, Boolean> localGuideSessionBooleanMap = localGuide.getScheduleMap();
            System.out.println(localGuideSessionBooleanMap.isEmpty());
            if (!localGuideSessionBooleanMap.isEmpty()){
                days = new ArrayList<>();
                startTimes = new ArrayList<>();
                isScheduledList = new ArrayList<>();
            for (Map.Entry<LocalGuideSession,Boolean> entry:localGuideSessionBooleanMap.entrySet()) {
               LocalDate day = entry.getKey().getDay();
               LocalTime startTime = entry.getKey().getStartTime();
               Boolean isScheduled = entry.getValue();
               days.add(day);
               startTimes.add(startTime);
               isScheduledList.add(isScheduled);
            }}
            LocalGuideGetDto localGuideGetDto = localGuideMapper.localGuideToLocalGuideGetDto(localGuide,company, days, startTimes, isScheduledList);
            localGuideBaseDtoList.add(localGuideGetDto);
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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/available-local-guides")
    public List<Integer> getAvailableLocalGuides(@RequestParam(value = "locationId") Integer locationId,@RequestParam(value = "day") String day,
            @RequestParam(value = "startTime") String startTime,@RequestParam(value = "endTime") String endTime){
        List<Integer> localGuideIds = new ArrayList<>();
        List<LocalGuide> localGuides = scheduleService.getAvailableLocalGuides(locationId,LocalDate.parse(day),LocalTime.parse(startTime), LocalTime.parse(endTime));
        for (LocalGuide localGuide: localGuides) {
            localGuideIds.add(localGuide.getLocalGuideId());
        }
        return localGuideIds;
    }


}
