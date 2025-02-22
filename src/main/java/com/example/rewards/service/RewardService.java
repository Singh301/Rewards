package com.example.rewards.service;

import com.example.rewards.dto.RewardResponse;
import com.example.rewards.exception.NoDataFoundException;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for handling reward calculations.
 */
@Service
public class RewardService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Retrieves reward points for transactions in the past three months.
     *
     * @return List of RewardResponse containing reward points per customer
     */
    public List<RewardResponse> getRewardPoints() {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        LocalDate today = LocalDate.now();

        List<Transaction> transactions = transactionRepository.findByTransactionDateBetween(threeMonthsAgo, today);

        if (transactions.isEmpty()) {
            throw new NoDataFoundException("No transactions found for the past three months.");
        }

        Map<String, Map<String, Integer>> customerRewards = new HashMap<>();

        for (Transaction transaction : transactions) {
            String customer = transaction.getCustomerName();
            String month = transaction.getTransactionDate().getMonth().toString();
            int points = calculateRewardPoints(transaction.getAmount());

            customerRewards.putIfAbsent(customer, new HashMap<>());
            customerRewards.get(customer).put(month, customerRewards.get(customer).getOrDefault(month, 0) + points);
        }

        return customerRewards.entrySet().stream()
                .map(entry -> new RewardResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Calculates reward points based on the transaction amount.
     *
     * @param amount The transaction amount
     * @return The calculated reward points
     */
    private int calculateRewardPoints(double amount) {
        int points = 0;
        if (amount > 50) {
            points += (int) (amount - 50);
        }
        if (amount > 100) {
            points += (int) (amount - 100);
        }
        return points;
    }
}

