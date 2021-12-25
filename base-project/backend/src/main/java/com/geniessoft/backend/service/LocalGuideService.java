package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.*;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.Location;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface LocalGuideService {
    LocalGuide findLocalGuideById(int localGuideId);
    LocalGuide updateLocalGuide(LocalGuideUpdateDto localGuideUpdateDto);
    LocalGuide DeleteLocalGuide(int localGuideId);
    LocalGuide saveLocalGuide(LocalGuideBaseDto localGuideBaseDto);
    List<LocalGuide> findAllLocalGuides();
    List<LocalGuide> getLocalGuidesByLocationId(Integer locationId);
    public void updateSchedule(LocalGuide localGuide);
    List<LocalGuideGetFrontendDto> getFrontendDtoList();

    List<ContentDto> getLocalGuideContents(int locationId);

    void addLocalGuideContent(int localGuideId, MultipartFile file, String content_text);
}
