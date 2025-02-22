package com.example.rewards.dto;

import java.util.Map;

/**
 * DTO for reward response containing customer reward points.
 */
public class RewardResponse {
    private String customerName;
    private Map<String, Integer> monthlyRewards;

    /**
     * Constructor for RewardResponse.
     *
     * @param customerName   The name of the customer
     * @param monthlyRewards A map containing month names as keys and reward points as values
     */
    public RewardResponse(String customerName, Map<String, Integer> monthlyRewards) {
        this.customerName = customerName;
        this.monthlyRewards = monthlyRewards;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Map<String, Integer> getMonthlyRewards() {
        return monthlyRewards;
    }
}