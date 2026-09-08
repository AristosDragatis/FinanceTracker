package com.example.financetracker.exception;

/** Thrown when creating a resource that already exists (e.g. duplicate email). Maps to HTTP 409. */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
