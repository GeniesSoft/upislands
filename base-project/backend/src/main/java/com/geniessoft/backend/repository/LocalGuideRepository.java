package com.geniessoft.backend.repository;
import com.geniessoft.backend.model.LocalGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalGuideRepository extends JpaRepository<LocalGuide, Integer>{

    Optional<LocalGuide> findByLocalGuideId(Integer integer);
    Optional<LocalGuide> deleteLocalGuideByLocalGuideId(Integer integer);
    @Override
    List<LocalGuide> findAll();
}
