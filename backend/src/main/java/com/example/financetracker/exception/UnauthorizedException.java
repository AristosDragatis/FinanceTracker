package com.example.financetracker.exception;

/** Thrown on failed authentication (bad credentials, invalid token). Maps to HTTP 401. */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
