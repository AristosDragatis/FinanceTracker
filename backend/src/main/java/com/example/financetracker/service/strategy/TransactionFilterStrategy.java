package com.example.financetracker.service.strategy;

import com.example.financetracker.domain.Transaction;

import java.util.List;

// Strategy Pattern
public interface TransactionFilterStrategy {
    List<Transaction> filter(List<Transaction> transactions, String value);
}
