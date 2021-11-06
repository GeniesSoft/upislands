package com.geniessoft.backend.service;

import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.User;

import java.util.List;
import java.util.Map;

public interface AnalysisService {

    //------------------------------User Analysis-----------------------------//
    User findMostBookedUser();
    List<User> findBookedUsersByDescOrder();
    Map<User,Integer> makeUserCountMap();

    //------------------------------Location Analysis-----------------------------//
    Map<Location,Integer> makeLocationCountMap();
    List<Location> findBookedLocationsByBookingDescOrder();
    Location findMostBookedLocation();
    Map<Location,Double> findLocationsByRatingDescOrder();

    //------------------------------LocalGuide Analysis-----------------------------//
    Map<LocalGuide,Double> findLocalGuidesByRatingDescOrder();
    LocalGuide findMostBookedLocalGuide();
    List<LocalGuide> findLocalGuidesByBookingDescOrder();
    Map<LocalGuide,Integer> makeLocalGuideCountMap();
}
