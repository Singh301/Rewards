package com.example.rewards.controller;

import com.example.rewards.dto.RewardResponse;
import com.example.rewards.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling reward-related requests.
 */
@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    /**
     * Retrieves reward points for customers.
     *
     * @return ResponseEntity containing a list of RewardResponse objects
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RewardResponse>> getRewards() {
        List<RewardResponse> response = rewardService.getRewardPoints();
        return ResponseEntity.ok(response);
    }
}