package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.LocalGuideContent;
import com.geniessoft.backend.model.LocationContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalGuideContentRepository extends JpaRepository<LocalGuideContent, Integer> {

    List<LocalGuideContent> findAllByLocalGuide_LocalGuideId(int localGuideId);
}
