package com.healthify.repository;

import com.healthify.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengesRepository extends JpaRepository<Challenge, Long> {
    // Add custom query methods if needed, e.g.:
    // List<Challenge> findByStatus(String status);
}
