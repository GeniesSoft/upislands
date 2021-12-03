package com.geniessoft.backend.repository;

import com.geniessoft.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query
    Optional<User> findFirstByEmailAddressEquals(@Param(value = "emailAddress") String emailAddress);

    Optional<User> findFirstByEmailAddressEqualsAndDeletedIsFalse(@Param(value = "emailAddress") String emailAddress);

    Optional<User> findByUserId(long userId);
    
    List<User> findAllByDeletedFalse();

    Optional<User> findByUserId(int userId);
}
