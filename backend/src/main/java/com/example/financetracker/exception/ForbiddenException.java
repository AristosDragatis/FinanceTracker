package com.example.financetracker.exception;

/** Thrown when the current user is not allowed to act on a resource. Maps to HTTP 403. */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
