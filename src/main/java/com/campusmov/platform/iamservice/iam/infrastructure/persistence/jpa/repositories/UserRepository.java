package com.campusmov.platform.iamservice.iam.infrastructure.persistence.jpa.repositories;

import com.campusmov.platform.iamservice.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    void deleteAllByVerifyAccountExpiresAtBefore(LocalDateTime verifyAccountExpiresAt);
}
