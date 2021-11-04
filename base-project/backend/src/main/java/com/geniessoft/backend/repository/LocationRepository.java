package com.geniessoft.backend.repository;


import com.geniessoft.backend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {

    Optional<Location> findLocationByLocationIdAndDeletedIsFalse(Integer locationId);
    Optional<Location> findFirstByLocationNameEquals(String name);
}
