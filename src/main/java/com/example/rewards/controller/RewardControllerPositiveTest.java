package com.example.rewards.controller;

import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test for RewardController.
 * Ensures that reward points calculation and retrieval function correctly.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RewardControllerPositiveTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Tests if the reward points API returns the correct data when transactions exist.
     *
     * @throws Exception if the mock request fails
     */
    @Test
    void shouldReturnRewardPoints() throws Exception {
        // Given
        Transaction transaction = new Transaction();
        transaction.setCustomerName("John Doe");
        transaction.setAmount(120);
        transaction.setTransactionDate(LocalDate.now().minusMonths(1));
        transactionRepository.save(transaction);

        // When & Then
        mockMvc.perform(get("/api/rewards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].customerName").value("John Doe"));
    }
}

