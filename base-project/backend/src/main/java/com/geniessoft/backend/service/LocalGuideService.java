package com.geniessoft.backend.service;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.LocalGuide;

import java.util.List;

public interface LocalGuideService {
    LocalGuide findLocalGuideById(int localGuideId);
    LocalGuide updateLocalGuide(LocalGuideUpdateDto localGuideUpdateDto);
    LocalGuide DeleteLocalGuide(int localGuideId);
    LocalGuide saveLocalGuide(LocalGuideBaseDto localGuideBaseDto);
    LocalGuide findMostBookedLocalGuide();
    List<LocalGuide> findLocalGuidesByAscOrder();
}
