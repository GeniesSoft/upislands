package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.ContentDto;
import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideGetFrontendDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.repository.LocalGuideRepository;
import com.geniessoft.backend.service.CompanyService;
import com.geniessoft.backend.service.LocalGuideContentService;
import com.geniessoft.backend.service.LocalGuideService;
import com.geniessoft.backend.service.LocationService;
import com.geniessoft.backend.utility.bucket.BucketName;
import com.geniessoft.backend.utility.bucket.FolderNames;
import com.geniessoft.backend.utility.mapper.LocalGuideMapper;
import com.geniessoft.backend.utility.mapper.LocationMapper;
import com.geniessoft.backend.utility.schedule.LocalGuideScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class LocalGuideServiceImpl implements LocalGuideService {

    private final CompanyService companyService;
    private final LocalGuideMapper localGuideMapper;
    private final LocalGuideRepository localGuideRepository;
    private final LocalGuideScheduler localGuideScheduler;
    private final LocationService locationService;
    private final FileStoreService fileStoreService;
    private final LocalGuideContentService localGuideContentService;

    @Override
    public LocalGuide findLocalGuideById(int localGuideId) {
        Optional<LocalGuide> localGuide = localGuideRepository.findByLocalGuideId(localGuideId);
        return localGuide.orElseThrow(() -> new EntityNotFoundException("Local Guide is not found."));
    }

    @Transactional
    @Override
    public LocalGuide updateLocalGuide(LocalGuideUpdateDto localGuideUpdateDto) {
        LocalGuide localGuide = findLocalGuideById(localGuideUpdateDto.getLocalGuideId());
        localGuide.setLocalGuideName(localGuide.getLocalGuideName());
        localGuideMapper.updateLocalGuide(localGuide, localGuideUpdateDto);
        return localGuideRepository.save(localGuide);
    }

    @Transactional
    @Override
    public LocalGuide DeleteLocalGuide(int localGuideId) {
        LocalGuide localGuide = findLocalGuideById(localGuideId);
        localGuideRepository.deleteLocalGuideByLocalGuideId(localGuideId);
        return  localGuide;
    }

    @Transactional
    @Override
    public LocalGuide saveLocalGuide(LocalGuideBaseDto localGuideBaseDto) {
        Company company = companyService.findCompanyById(localGuideBaseDto.getCompanyId());
        LocalGuide localGuide = localGuideMapper.localGuideBaseDtoToLocalGuide(localGuideBaseDto);
        localGuide.setCompany(company);
        localGuide.setLocalGuideName(localGuideBaseDto.getLocalGuideName());

        return localGuideRepository.save(localGuide);
    }

    @Transactional
    @Override
    public void updateSchedule(LocalGuide localGuide) {
        localGuideRepository.save(localGuide);
    }



    /*@Override
    public LocalGuide findMostBookedLocalGuide() {
        Map<LocalGuide,Integer> localGuideCountMap = makeLocalGuideCountMap();
        LocalGuide localGuide = Collections.max(localGuideCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        return localGuide;
    }*/

    /*@Override
    public List<LocalGuide> findLocalGuidesByBookingDescOrder() {
        Map<LocalGuide,Integer> localGuideCountMap = makeLocalGuideCountMap();
        localGuideCountMap = localGuideCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<LocalGuide> localGuides = new ArrayList<>(localGuideCountMap.keySet());
        return localGuides;

    }*/

    /*@Override
    public Map<LocalGuide,Double> findLocalGuidesByRatingDescOrder() {
        List<LocalGuide> localGuides = findAllLocalGuides();
        Map<LocalGuide,Double> localGuideAverages = new HashMap<>();
        for (LocalGuide localGuide: localGuides) {
            localGuideAverages.put(localGuide,reviewService.findReviewAverageByLocalGuideId(localGuide.getLocalGuideId()));
        }
        localGuideAverages = localGuideAverages.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return localGuideAverages;
    }*/

    @Override
    public List<LocalGuide> findAllLocalGuides() {
        return localGuideRepository.findAll();
    }

    @Override
    public List<LocalGuide> getLocalGuidesByLocationId(Integer locationId) {
        Location location = locationService.findLocationById(locationId);

        List<LocalGuide> allLocalGuides = findAllLocalGuides();

        List<LocalGuide> localGuides = new ArrayList<>();
        for (LocalGuide localguide: allLocalGuides) {
            if (localguide.getCompany().getLocationList().contains(location)){
                localGuides.add(localguide);
            }
        }

        return localGuides;
    }

    /*private Map<LocalGuide,Integer> makeLocalGuideCountMap(){
        List<Booking> bookings = bookingService.findAllBookingsByLocalGuideOrder();
        Map<LocalGuide,Integer> locationCountMap = new HashMap<>();
        for (int i = 0;i<bookings.size();i++) {

            LocalGuide localGuide = findLocalGuideById(bookings.get(i).getLocalGuide().getLocalGuideId());
            if (i == 0){
                locationCountMap.put(localGuide, 1);}
            if (i !=0){
                if (localGuide.equals(findLocalGuideById(bookings.get(i-1).getBookingLocation().getLocationId()))){
                    locationCountMap.merge(localGuide, 1, Integer::sum);
                }
                else {
                    locationCountMap.put(localGuide,1);
                }
            }
        }
        return locationCountMap;
    }*/

    @Override
    public void addLocalGuideContent(int localGuideId, MultipartFile file, String content_text) {

        LocalGuide localGuide = findLocalGuideById(localGuideId);
        Map<String,String> metadata = fileStoreService.getMetadata(file);

        String path = String.format("%s/%s",
                BucketName.BUCKET_NAME.getBucketName(),
                FolderNames.local_guide_contents +"/"+ localGuide.getLocalGuideId());

        String fileName = String.format("%s-%s",
                System.currentTimeMillis(),
                file.getOriginalFilename());

        try {

            localGuideContentService.saveLocalGuideContent
                    (fileName,path,file.getContentType(),content_text,localGuide);
            fileStoreService.upload(path,fileName,Optional.of(metadata),file.getInputStream());
            System.out.println("LocalGuide content is uploaded");
        }
        catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<ContentDto> getLocalGuideContents(int locationId) {
        return null;
    }

    @Override
    public List<LocalGuideGetFrontendDto> getFrontendDtoList() {
        var list = findAllLocalGuides().stream().map(LocalGuideMapper.INSTANCE::localGuideToLocalGuideGetFrontendDto).toList();

        list.forEach(localGuide -> {
            localGuide.setGallery(localGuideContentService.getLocalGuideContentGallery(localGuide.getId(),"image"));
            localGuide.setVideoGallery(localGuideContentService.getLocalGuideContentGallery(localGuide.getId(),"video"));
        });

        return list;
    }
}
