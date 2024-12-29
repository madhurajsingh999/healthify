package com.healthify.controller;

import com.healthify.entity.Reward;
import com.healthify.service.KafkaProducerService;
import com.healthify.service.RewardsService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rewards")
public class RewardsController {

    private final RewardsService rewardsService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody String message) {
        log.info(message);
        kafkaProducerService.sendMessage(message);
    }

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {
        return ResponseEntity.ok(rewardsService.getAllRewards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable Long id) {
        return rewardsService.getRewardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Reward> createReward(@RequestBody Reward reward) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rewardsService.createReward(reward));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable Long id, @RequestBody Reward reward) {
        try {
            return ResponseEntity.ok(rewardsService.updateReward(id, reward));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReward(@PathVariable Long id) {
        try {
            rewardsService.deleteReward(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
