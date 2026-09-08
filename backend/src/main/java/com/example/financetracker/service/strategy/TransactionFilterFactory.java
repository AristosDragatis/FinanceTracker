package com.example.financetracker.service.strategy;

import com.example.financetracker.domain.FilterType;
import org.springframework.stereotype.Component;

@Component
public class TransactionFilterFactory {

    private final CategoryFilter categoryFilter;
    private final AmountFilter amountFilter;
    private final DateFilter dateFilter;

    public TransactionFilterFactory(CategoryFilter categoryFilter, AmountFilter amountFilter, DateFilter dateFilter){
        this.amountFilter = amountFilter;
        this.categoryFilter = categoryFilter;
        this.dateFilter = dateFilter;
    }

    public TransactionFilterStrategy getStrategy(FilterType type){
        return switch(type){
            case CATEGORY -> categoryFilter;
            case AMOUNT -> amountFilter;
            case DATE -> dateFilter;
            default -> throw new IllegalArgumentException("Unkown filter type! " + type);
        };
    }
}
