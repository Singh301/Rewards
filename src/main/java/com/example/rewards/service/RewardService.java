package com.example.rewards.service;

import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Service class for calculating and retrieving reward points based on transactions.
 */
@Service
public class RewardService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Calculates reward points for transactions in the past three months.
     *
     * @return A map containing customer names and their corresponding reward points per month.
     */
    public Map<String, Map<String, Integer>> getRewardPoints() {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        LocalDate today = LocalDate.now();

        List<Transaction> transactions = transactionRepository.findByTransactionDateBetween(threeMonthsAgo, today);
        Map<String, Map<String, Integer>> customerRewards = new HashMap<>();

        for (Transaction transaction : transactions) {
            String customer = transaction.getCustomerName();
            String month = transaction.getTransactionDate().getMonth().toString();
            int points = calculateRewardPoints(transaction.getAmount());

            customerRewards.putIfAbsent(customer, new HashMap<>());
            customerRewards.get(customer).put(month, customerRewards.get(customer).getOrDefault(month, 0) + points);
        }

        return customerRewards;
    }

    /**
     * Calculates reward points based on the transaction amount.
     *
     * @param amount The transaction amount.
     * @return The calculated reward points.
     */
    private int calculateRewardPoints(double amount) {
        int points = 0;
        if (amount > 50) {
            points += (int) (amount - 50); // 1 point per dollar above $50
        }
        if (amount > 100) {
            points += (int) (amount - 100); // Additional 1 point per dollar above $100
        }
        return points;
    }
}
