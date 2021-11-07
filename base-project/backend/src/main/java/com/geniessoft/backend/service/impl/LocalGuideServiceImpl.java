package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.dto.LocalGuideBaseDto;
import com.geniessoft.backend.dto.LocalGuideUpdateDto;
import com.geniessoft.backend.model.Booking;
import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.JetSkiDetails;
import com.geniessoft.backend.model.LocalGuide;
import com.geniessoft.backend.repository.LocalGuideRepository;
import com.geniessoft.backend.service.BookingService;
import com.geniessoft.backend.service.CompanyService;
import com.geniessoft.backend.service.LocalGuideService;
import com.geniessoft.backend.service.ReviewService;
import com.geniessoft.backend.utility.mapper.LocalGuideMapper;
import com.geniessoft.backend.utility.schedule.BooleanScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LocalGuideServiceImpl implements LocalGuideService {
    private final CompanyService companyService;
    private final BookingService bookingService;
    private final LocalGuideMapper localGuideMapper;
    private final LocalGuideRepository localGuideRepository;
    private final BooleanScheduler booleanScheduler;

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
    public void updateSchedule(Integer localGuideId, LocalDate day, LocalTime startTime, LocalTime endTime, Integer numOfJetSkiesToSchedule) {
        LocalGuide localGuide = findLocalGuideById(localGuideId);

        booleanScheduler.setScheduleMap(localGuide.getScheduleMap());

        localGuide.setScheduleMap(booleanScheduler.updateSchedule(day, startTime, endTime, true));

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
}
