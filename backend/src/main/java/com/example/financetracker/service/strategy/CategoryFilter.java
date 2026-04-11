package com.example.financetracker.service.strategy;


import com.example.financetracker.domain.Transaction;
import org.springframework.stereotype.Component;

@Component
public class CategoryFilter extends AbstractTransactionFilterStrategy{

    @Override
    protected boolean matchCondition(Transaction transaction, String value){
        // make the comparison only
        return transaction.getCategory().getName().equalsIgnoreCase(value);
    }
}
