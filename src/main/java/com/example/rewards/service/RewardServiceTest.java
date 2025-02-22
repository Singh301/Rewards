package com.example.rewards.service;

import com.example.rewards.exception.NoDataFoundException;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for RewardService.
 * Validates business logic for calculating reward points.
 */
class RewardServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardService rewardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests if the service throws an exception when no transactions are found.
     */
    @Test
    void shouldThrowExceptionWhenNoTransactionsFound() {
        when(transactionRepository.findByTransactionDateBetween(any(), any())).thenReturn(List.of());

        assertThrows(NoDataFoundException.class, () -> rewardService.getRewardPoints());
    }
}