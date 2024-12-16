package com.healthify.ServiceImpl;

import com.healthify.entity.Challenge;
import com.healthify.repository.ChallengesRepository;
import com.healthify.service.ChallengesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengesServiceImpl implements ChallengesService {

    private final ChallengesRepository challengesRepository;

    public ChallengesServiceImpl(ChallengesRepository challengesRepository) {
        this.challengesRepository = challengesRepository;
    }

    @Override
    public List<Challenge> getAllChallenges() {
        return challengesRepository.findAll();
    }

    @Override
    public Optional<Challenge> getChallengeById(Long id) {
        return challengesRepository.findById(id);
    }

    @Override
    public Challenge createChallenge(Challenge challenge) {
        return challengesRepository.save(challenge);
    }

    @Override
    public Challenge updateChallenge(Long id, Challenge challenge) {
        return challengesRepository.findById(id).map(existingChallenge -> {
            existingChallenge.setChallengeName(challenge.getChallengeName());
            existingChallenge.setChallengeDescription(challenge.getChallengeDescription());
            existingChallenge.setStartDate(challenge.getStartDate());
            existingChallenge.setEndDate(challenge.getEndDate());
            existingChallenge.setStatus(challenge.getStatus());
            existingChallenge.setLastUpdatedBy(challenge.getLastUpdatedBy());
            existingChallenge.setLastUpdatedAt(challenge.getLastUpdatedAt());
            return challengesRepository.save(existingChallenge);
        }).orElseThrow(() -> new RuntimeException("Challenge not found with id: " + id));
    }

    @Override
    public void deleteChallenge(Long id) {
        if (challengesRepository.existsById(id)) {
            challengesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Challenge not found with id: " + id);
        }
    }
}
