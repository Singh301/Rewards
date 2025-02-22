package com.example.rewards.exception;

/**
 * Custom exception for handling scenarios where no data is found.
 */
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
