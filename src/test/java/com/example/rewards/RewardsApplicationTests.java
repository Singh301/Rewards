package com.example.rewards;

import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.rewards.service.RewardService;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RewardsApplicationTests {

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private RewardService rewardService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetRewardPoints() {
		Transaction t1 = new Transaction();
		t1.setCustomerName("Alice");
		t1.setAmount(120);
		t1.setTransactionDate(LocalDate.now().minusDays(10));

		Transaction t2 = new Transaction();
		t2.setCustomerName("Bob");
		t2.setAmount(75);
		t2.setTransactionDate(LocalDate.now().minusDays(20));

		when(transactionRepository.findByTransactionDateBetween(
				LocalDate.now().minusMonths(3), LocalDate.now())).thenReturn(Arrays.asList(t1, t2));

		Map<String, Map<String, Integer>> rewards = rewardService.getRewardPoints();

		assertEquals(90, rewards.get("Alice").values().iterator().next()); // 20 + 2*20
		assertEquals(25, rewards.get("Bob").values().iterator().next());  // 25 points
	}
}
