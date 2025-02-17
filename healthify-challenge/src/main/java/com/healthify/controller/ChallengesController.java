package com.healthify.controller;

import com.healthify.entity.Challenge;
import com.healthify.service.ChallengesService;
import com.healthify.service.KafkaProducerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/challenges")
public class ChallengesController {

    private final ChallengesService challengesService;

    public ChallengesController(ChallengesService challengesService) {
        this.challengesService = challengesService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return ResponseEntity.ok(challengesService.getAllChallenges());
    }

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody String message) {
        log.info(message);
        kafkaProducerService.sendMessage(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Long id) {
        return challengesService.getChallengeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {
        return ResponseEntity.status(HttpStatus.CREATED).body(challengesService.createChallenge(challenge));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Challenge> updateChallenge(@PathVariable Long id, @RequestBody Challenge challenge) {
        try {
            return ResponseEntity.ok(challengesService.updateChallenge(id, challenge));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        try {
            challengesService.deleteChallenge(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
