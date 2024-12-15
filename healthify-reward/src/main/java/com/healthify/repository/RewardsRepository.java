package com.healthify.repository;

import com.healthify.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardsRepository extends JpaRepository<Reward, Long> {
    // Add custom query methods if needed
}
