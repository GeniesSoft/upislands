package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.LocalGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalGuideRepository extends JpaRepository<LocalGuide,Integer> {

    Optional<LocalGuide> findLocalGuideByLocalGuideId(Integer integer);
}
