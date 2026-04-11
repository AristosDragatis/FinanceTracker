package com.example.financetracker.service.strategy;

import com.example.financetracker.domain.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AmountFilter extends AbstractTransactionFilterStrategy{

    @Override
    protected boolean matchCondition(Transaction transaction, String value){
        BigDecimal value_num = new BigDecimal(value);
        return transaction.getAmount().compareTo(value_num) >= 0;
    }
}
