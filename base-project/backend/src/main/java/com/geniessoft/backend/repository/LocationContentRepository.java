package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.LocationContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationContentRepository extends JpaRepository<LocationContent, Integer> {
}
