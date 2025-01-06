package com.healthify.service;

import com.healthify.entity.*;

import java.util.List;
import java.util.Optional;

public interface ChallengesService {
    List<Challenge> getAllChallenges();

    Optional<Challenge> getChallengeById(Long id);

    Challenge createChallenge(Challenge challenge);

    Challenge updateChallenge(Long id, Challenge challenge);

    void deleteChallenge(Long id);
}
