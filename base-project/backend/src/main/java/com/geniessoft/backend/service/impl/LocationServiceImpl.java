package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.ContentDto;
import com.geniessoft.backend.dto.LocationSaveDto;
import com.geniessoft.backend.dto.LocationUpdateDto;
import com.geniessoft.backend.model.*;
import com.geniessoft.backend.repository.LocationRepository;
import com.geniessoft.backend.service.AddressService;
import com.geniessoft.backend.service.ContentService;
import com.geniessoft.backend.service.LocationContentService;
import com.geniessoft.backend.service.LocationService;
import com.geniessoft.backend.utility.bucket.BucketName;
import com.geniessoft.backend.utility.bucket.FolderNames;
import com.geniessoft.backend.utility.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper mapper;
    private final AddressService addressService;
    private final FileStoreService fileStoreService;
    private final ContentService contentService;
    private final LocationContentService locationContentService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Location saveLocation(LocationSaveDto locationSaveDto) {
        checkLocationName(locationSaveDto.getLocationName());
        Location location = mapper.locationSaveDtoToLocation(locationSaveDto);
        Address address = mapper.locationSaveDtoToAddress(locationSaveDto);
        addressService.saveAddress(address);
        location.setAddress(address);
        return locationRepository.save(location);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public Location updateLocation(LocationUpdateDto locationUpdateDto){
        Location location = findLocationById(locationUpdateDto.getLocationId());
        //checkLocationName(location.getLocationName());
        mapper.updateLocation(location, locationUpdateDto);
        locationRepository.save(location);
        return location;
    }

    @Override
    public void checkLocationName(String locationName) {
        Optional<Location> location = locationRepository.findFirstByLocationNameEquals(locationName.trim());

        if (location.isPresent()){
            if (location.get().isDeleted()){
                throw new EntityExistsException("This Location is deleted.");
            }
            else
                throw new EntityExistsException("This location already exists.");
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

    @Override
    public Location findLocationById(int locationId) {
        Optional<Location> location = locationRepository
                .findLocationByLocationIdAndDeletedIsFalse(locationId);
        return location.orElseThrow(() -> new EntityNotFoundException("Location is not found."));

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
}
