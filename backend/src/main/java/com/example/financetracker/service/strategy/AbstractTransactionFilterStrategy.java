package com.example.financetracker.service.strategy;

import com.example.financetracker.domain.Transaction;
import java.util.List;

// Template Method
public abstract class AbstractTransactionFilterStrategy implements TransactionFilterStrategy {

    // parent loops (stream) into the whole list
    @Override
    public final List<Transaction> filter(List<Transaction> transactions, String value) {
        return transactions.stream()
                .filter(t -> matchCondition(t, value))
                .toList();
    }

    protected abstract boolean matchCondition(Transaction transaction, String value);
}