package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Optional<Company> findByCompanyIdAndDeletedIsFalse(Integer companyId);
    Optional<Company> findFirstByCompanyNameEquals(String name);
    Optional<Company> findFirstByJobOwnerUserId(int userId);
}
