package com.healthify.service;

import com.healthify.entity.Reward;

import java.util.List;
import java.util.Optional;

public interface RewardsService {
    List<Reward> getAllRewards();

    Optional<Reward> getRewardById(Long id);

    Reward createReward(Reward reward);

    Reward updateReward(Long id, Reward reward);

    void deleteReward(Long id);
}
