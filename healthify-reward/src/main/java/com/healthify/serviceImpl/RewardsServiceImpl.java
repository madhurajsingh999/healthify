package com.healthify.serviceImpl;

import com.healthify.entity.Reward;
import com.healthify.repository.RewardsRepository;
import com.healthify.service.RewardsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardsServiceImpl implements RewardsService {

    private final RewardsRepository rewardsRepository;

    public RewardsServiceImpl(RewardsRepository rewardsRepository) {
        this.rewardsRepository = rewardsRepository;
    }

    @Override
    public List<Reward> getAllRewards() {
        return rewardsRepository.findAll();
    }

    @Override
    public Optional<Reward> getRewardById(Long id) {
        return rewardsRepository.findById(id);
    }

    @Override
    public Reward createReward(Reward reward) {
        return rewardsRepository.save(reward);
    }

    @Override
    public Reward updateReward(Long id, Reward reward) {
        return rewardsRepository.findById(id).map(existingReward -> {
            existingReward.setChallengeId(reward.getChallengeId());
            existingReward.setRewardName(reward.getRewardName());
            existingReward.setDescription(reward.getDescription());
            existingReward.setEligibilityCriteria(reward.getEligibilityCriteria());
            existingReward.setRewardType(reward.getRewardType());
            existingReward.setPointValue(reward.getPointValue());
            existingReward.setQuantityAvailable(reward.getQuantityAvailable());
            existingReward.setStartDate(reward.getStartDate());
            existingReward.setEndDate(reward.getEndDate());
            existingReward.setStatus(reward.getStatus());
            existingReward.setClaimedBy(reward.getClaimedBy());
            existingReward.setDateClaimed(reward.getDateClaimed());
            existingReward.setDeliveryStatus(reward.getDeliveryStatus());
            existingReward.setDeliveryMethod(reward.getDeliveryMethod());
            existingReward.setLastUpdatedBy(reward.getLastUpdatedBy());
            existingReward.setLastUpdatedAt(reward.getLastUpdatedAt());
            return rewardsRepository.save(existingReward);
        }).orElseThrow(() -> new RuntimeException("Reward not found with id: " + id));
    }

    @Override
    public void deleteReward(Long id) {
        if (rewardsRepository.existsById(id)) {
            rewardsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Reward not found with id: " + id);
        }
    }
}
