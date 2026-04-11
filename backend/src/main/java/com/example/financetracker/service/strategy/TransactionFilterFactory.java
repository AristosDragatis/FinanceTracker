package com.example.financetracker.service.strategy;

import com.example.financetracker.domain.FilterType;
import org.springframework.stereotype.Component;

@Component
public class TransactionFilterFactory {

    private final CategoryFilter categoryFilter;
    private final AmountFilter amountFilter;

    public TransactionFilterFactory(CategoryFilter categoryFilter, AmountFilter amountFilter){
        this.amountFilter = amountFilter;
        this.categoryFilter = categoryFilter;
    }

    public TransactionFilterStrategy getStrategy(FilterType type){
        return switch(type){
            case CATEGORY -> categoryFilter;
            case AMOUNT -> amountFilter;
            default -> throw new IllegalArgumentException("Unkown filter type! " + type);
        };
    }
}
