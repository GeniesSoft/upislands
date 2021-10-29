package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.CompanyContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyContentRepository extends JpaRepository<CompanyContent, Integer> {
}
