package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.Booking;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.User;
import com.geniessoft.backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalysisServiceServiceImpl implements AnalysisService {

    private final BookingService bookingService;
    private final UserService userService;
    private final LocationService locationService;
    private final ReviewService reviewService;
    private final LocalGuideService localGuideService;


    //----------------------User Analysis---------------------------//

    @Override
    public User findMostBookedUser() {
        Map<User,Integer> userCountMap = makeUserCountMap();
        User user = Collections.max(userCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        return user;
    }

    @Override
    public List<User> findBookedUsersByDescOrder() {
        Map<User,Integer> userCountMap = makeUserCountMap();
        userCountMap = userCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<User> users = new ArrayList<>(userCountMap.keySet());
        return users;
    }

    @Override
    public Map<User, Integer> makeUserCountMap() {
        List<Booking> bookings = bookingService.findAllBookingsByUserOrder();
        Map<User,Integer> locationCountMap = new HashMap<>();
        for (int i = 0;i<bookings.size();i++) {

            User user = userService.findUser(bookings.get(i).getUser().getUserId());
            if (i == 0){
                locationCountMap.put(user, 1);}
            if (i !=0){
                if (user.equals(userService.findUser(bookings.get(i-1).getBookingLocation().getLocationId()))){
                    locationCountMap.merge(user, 1, Integer::sum);
                }
                else {
                    locationCountMap.put(user,1);
                }
            }
        }
        return locationCountMap;
    }

    //----------------------Location Analysis---------------------------//
    @Override
    public Map<Location, Integer> makeLocationCountMap() {
        List<Booking> bookings = bookingService.findAllBookingsByLocationOrder();
        Map<Location,Integer> locationCountMap = new HashMap<>();
        for (int i = 0;i<bookings.size();i++) {

            Location location = locationService.findLocationById(bookings.get(i).getBookingLocation().getLocationId());
            if (i == 0){
                locationCountMap.put(location, 1);}
            if (i !=0){
                if (location.equals(locationService.findLocationById(bookings.get(i-1).getBookingLocation().getLocationId()))){
                    locationCountMap.merge(location, 1, Integer::sum);
                }
                else {
                    locationCountMap.put(location,1);
                }
            }
        }
        return locationCountMap;
    }

    @Override
    public List<Location> findBookedLocationsByBookingDescOrder() {
        Map<Location,Integer> locationCountMap = makeLocationCountMap();
        locationCountMap = locationCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<Location> locations = new ArrayList<>(locationCountMap.keySet());
        return locations;
    }

    @Override
    public Location findMostBookedLocation() {
        Map<Location,Integer> locationCountMap = makeLocationCountMap();
        Location mostBookedLocation = Collections.max(locationCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        return mostBookedLocation;
    }

    @Override
    public Map<Location, Double> findLocationsByRatingDescOrder() {
        List<Location> locations = locationService.findAllLocations();
        Map<Location,Double> locationAverageMap = new HashMap<>();
        for (Location location: locations) {
            locationAverageMap.put(location, reviewService.findReviewAverageByLocationId(location.getLocationId()));
        }

        locationAverageMap = locationAverageMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return locationAverageMap;
    }

    //------------------------------LocalGuide Analysis-----------------------------//
    @Override
    public Map<LocalGuide, Double> findLocalGuidesByRatingDescOrder() {
        List<LocalGuide> localGuides = localGuideService.findAllLocalGuides();
        Map<LocalGuide,Double> localGuideAverages = new HashMap<>();
        for (LocalGuide localGuide: localGuides) {
            localGuideAverages.put(localGuide,reviewService.findReviewAverageByLocalGuideId(localGuide.getLocalGuideId()));
        }
        localGuideAverages = localGuideAverages.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return localGuideAverages;
    }

    @Override
    public LocalGuide findMostBookedLocalGuide() {
        Map<LocalGuide,Integer> localGuideCountMap = makeLocalGuideCountMap();
        LocalGuide localGuide = Collections.max(localGuideCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        return localGuide;
    }

    @Override
    public List<LocalGuide> findLocalGuidesByBookingDescOrder() {
        Map<LocalGuide,Integer> localGuideCountMap = makeLocalGuideCountMap();
        localGuideCountMap = localGuideCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<LocalGuide> localGuides = new ArrayList<>(localGuideCountMap.keySet());
        return localGuides;
    }

    @Override
    public Map<LocalGuide, Integer> makeLocalGuideCountMap() {
        List<Booking> bookings = bookingService.findAllBookingsByLocalGuideOrder();
        Map<LocalGuide,Integer> locationCountMap = new HashMap<>();
        for (int i = 0;i<bookings.size();i++) {

            LocalGuide localGuide = localGuideService.findLocalGuideById(bookings.get(i).getLocalGuide().getLocalGuideId());
            if (i == 0){
                locationCountMap.put(localGuide, 1);}
            if (i !=0){
                if (localGuide.equals(localGuideService.findLocalGuideById(bookings.get(i-1).getBookingLocation().getLocationId()))){
                    locationCountMap.merge(localGuide, 1, Integer::sum);
                }
                else {
                    locationCountMap.put(localGuide,1);
                }
            }
        }
        return locationCountMap;
    }
}
