package com.example.rewards.controller;

import com.example.rewards.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for handling reward-related requests.
 */

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    /**
     * Retrieves reward points for customers based on their transactions.
     *
     * @return ResponseEntity containing a map of customer names and their reward points per month.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Map<String, Integer>>> getRewards() {
        Map<String, Map<String, Integer>> rewards = rewardService.getRewardPoints();
        return ResponseEntity.ok(rewards);
    }
}
