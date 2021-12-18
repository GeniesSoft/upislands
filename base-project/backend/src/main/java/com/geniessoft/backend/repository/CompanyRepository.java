package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Optional<Company> findByCompanyIdAndDeletedIsFalse(int companyId);
    Optional<Company> findFirstByCompanyNameEquals(String name);
    Optional<Company> findFirstByJobOwnerUserId(long userId);

    @Override
    List<Company> findAll();

    //List<Company> findCompanyByLocationList(List<Location> locations);
}
