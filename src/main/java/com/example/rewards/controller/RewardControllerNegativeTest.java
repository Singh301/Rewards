package com.example.rewards.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test to check negative scenarios for RewardController.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RewardControllerNegativeTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests if the API returns a 404 Not Found response when no transactions exist.
     *
     * @throws Exception if the mock request fails
     */
    @Test
    void shouldReturnNotFoundWhenNoDataExists() throws Exception {
        mockMvc.perform(get("/api/rewards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No transactions found for the past three months."));
    }
}