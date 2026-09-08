package com.example.financetracker.service.strategy;

import com.example.financetracker.domain.Transaction;
import org.springframework.stereotype.Component;

@Component
public class DateFilter extends AbstractTransactionFilterStrategy{

    @Override
    protected boolean matchCondition(Transaction transaction, String value){
        return transaction.getDate().toString().contains(value);
    }
}
