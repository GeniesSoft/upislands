package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.*;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.repository.LocationRepository;
import com.geniessoft.backend.service.*;
import com.geniessoft.backend.utility.bucket.BucketName;
import com.geniessoft.backend.utility.bucket.FolderNames;
import com.geniessoft.backend.utility.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    //private final ReviewService reviewService;
    private final FileStoreService fileStoreService;
    private final ContentService contentService;
    private final LocationContentService locationContentService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Location saveLocation(Location location) {

        checkLocationName(location.getLocationName());

        return locationRepository.save(location);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public Location updateLocation(Location location){
        checkLocationId(location.getLocationId());
        return locationRepository.save(location);
    }

    public void checkLocationId(Integer locationId) {
        Optional<Location> optionalLocation = locationRepository.findById(locationId);

        if (optionalLocation.isEmpty()) throw new EntityNotFoundException("Location not found");
    }

    @Override
    public void checkLocationName(String locationName) {
        Optional<Location> location = locationRepository.findFirstByLocationNameEquals(locationName.trim());

        if (location.isPresent()){
            if (location.get().isDeleted())
                throw new EntityNotFoundException("This Location is deleted");
            else
                throw new EntityExistsException("This location already exists");
        }
    }

    @Override
    public void deleteLocation(int locationId) {
        Location location = findLocationById(locationId);
        location.setDeleted(true);
        locationRepository.save(location);
    }

    @Override
    public Location findLocationByName(String locationName) {
        return null;
    }

    /*@Override
    public Location findMostBookedLocation() {
        Map<Location,Integer> locationCountMap = makeLocationCountMap();
        Location mostBookedLocation = Collections.max(locationCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        return mostBookedLocation;
    }*/


    /*@Override
    public List<Location> findBookedLocationsByBookingDescOrder() {
       Map<Location,Integer> locationCountMap = makeLocationCountMap();
        locationCountMap = locationCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<Location> locations = new ArrayList<>(locationCountMap.keySet());
        return locations;
    }

    @Override
    public Map<Location,Double> findLocationsByRatingDescOrder() {
        List<Location> locations = findAllLocations();
        Map<Location,Double> locationAverageMap = new HashMap<>();
        for (Location location: locations) {
            locationAverageMap.put(location, reviewService.findReviewAverageByLocationId(location.getLocationId()));
        }

        locationAverageMap = locationAverageMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return locationAverageMap;
    }*/

    @Override
    public List<Location> findAllLocations() {
        return locationRepository.findLocationsByDeletedIsFalse();
    }

    /*private Map<Location,Integer> makeLocationCountMap(){
        List<Booking> bookings = bookingService.findAllBookingsByLocationOrder();
        Map<Location,Integer> locationCountMap = new HashMap<>();
        for (int i = 0;i<bookings.size();i++) {

            Location location = findLocationById(bookings.get(i).getBookingLocation().getLocationId());
            if (i == 0){
                locationCountMap.put(location, 1);}
            if (i !=0){
                if (location.equals(findLocationById(bookings.get(i-1).getBookingLocation().getLocationId()))){
                    locationCountMap.merge(location, 1, Integer::sum);
                }
                else {
                    locationCountMap.put(location,1);
                }
            }
        }
        return locationCountMap;
    }*/

    @Override
    public Location findLocationById(int locationId) {
        Optional<Location> location = locationRepository
                .findLocationByLocationIdAndDeletedIsFalse(locationId);
        return location.orElseThrow(() -> new EntityNotFoundException("Location is not found."));

    }

    @Override
    public List<Location> findAllLocationsInAState(String state) {
        List<Location> locations = findAllLocations();
        List<Location> stateLocations = new ArrayList<>();
        for ( Location location: locations) {
            if (location.getAddress().getState().equals(state)) {
                stateLocations.add(location);
            }
        }
        return stateLocations;

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteLocationProfileImage(Location location) {
        Content content = location.getLocationProfileImage();
        contentService.deleteContent(content.getContentId());
        fileStoreService.delete(content.getContentPath(),content.getContentName());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addLocationProfileImage(int locationId, MultipartFile file) {

        // If there is location profile image then previous image deleted.
        Location location = findLocationById(locationId);
        if(location.getLocationProfileImage() != null){
            deleteLocationProfileImage(location);
        }

        Map<String,String> metadata = fileStoreService.getMetadata(file);
        String path = String.format("%s/%s", BucketName.BUCKET_NAME.getBucketName(), FolderNames.location_profile_images);
        String fileName = String.format("%s-%s", location.getLocationId(), file.getOriginalFilename());

        try {

            Content locationProfileImage = contentService.saveContent(fileName,path,file.getContentType());
            location.setLocationProfileImage(locationProfileImage);
            fileStoreService.upload(path,fileName,Optional.of(metadata),file.getInputStream());
            System.out.println("Location profile image is uploaded");
        }
        catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addLocationContent(int locationId, MultipartFile file, String content_text) {

        Location location = findLocationById(locationId);
        Map<String,String> metadata = fileStoreService.getMetadata(file);

        String path = String.format("%s/%s",
                BucketName.BUCKET_NAME.getBucketName(),
                FolderNames.location_contents +"/"+ location.getLocationId());

        String fileName = String.format("%s-%s",
                System.currentTimeMillis(),
                file.getOriginalFilename());

        try {

            locationContentService.saveLocationContent
                    (fileName,path,file.getContentType(),content_text,location);
            fileStoreService.upload(path,fileName,Optional.of(metadata),file.getInputStream());
            System.out.println("Location content is uploaded");
        }
        catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void deleteLocationContent(Integer locationContentId) {
        locationContentService.deleteLocationContent(locationContentId);
    }

    @Override
    public void updateLocationContent(Integer locationContentId, String contentText) {
        locationContentService.updateLocationContent(locationContentId, contentText);
    }

    @Override
    public List<ContentDto> getLocationContents(int locationId, int offset, int pageSize) {

        List<LocationContent> locationContentList =
                locationContentService.getLocationContentPage(locationId,offset,pageSize);

        return contentService.getContents(locationContentList);
    }

    @Override
    public byte[] getLocationProfileImage(int locationId) {
        Location location = findLocationById(locationId);
        return fileStoreService.download(
                location.getLocationProfileImage().getContentPath(),
                location.getLocationProfileImage().getContentName());
    }

    @Override
    public List<LocationGetFrontendDto> getFrontendDtoList() {

        var list = findAllLocations().stream().map(LocationMapper.INSTANCE::locationToLocationGetFrontendDto).toList();

        list.forEach(location -> {
            location.setGallery(locationContentService.getLocationGallery(location.getId(),"image"));
            location.setVideoGallery(locationContentService.getLocationGallery(location.getId(),"video"));
        });

        return list;
    }
}
