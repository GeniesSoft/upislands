package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.JetSkiDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JetSkiDetailsRepository extends JpaRepository<JetSkiDetails, Integer> {

    Optional<JetSkiDetails> findByCompany(Company company);

    Optional<JetSkiDetails> findByCompany_CompanyId(Integer companyId);

    void deleteByCompany_CompanyId(Integer companyId);

}