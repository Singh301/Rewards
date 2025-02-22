package com.example.rewards;

import com.example.rewards.dto.RewardResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for reward-related API endpoints.
 * Ensures proper handling of responses and data retrieval.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Tests if the rewards API returns a successful response when data is available.
     */
    @Test
    void testGetRewardsSuccess() {
        ResponseEntity<RewardResponse[]> response = restTemplate.getForEntity("/api/rewards", RewardResponse[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    /**
     * Tests if the API returns a 404 error when no data is available.
     */
    @Test
    void testGetRewardsNoDataFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/rewards", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("No transactions found in the last three months"));
    }
}
