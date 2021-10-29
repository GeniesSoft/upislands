package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content,Integer> {
}
