package com.example.financetracker.service;

import com.example.financetracker.domain.AppUser;
import com.example.financetracker.domain.Category;
import com.example.financetracker.domain.Transaction;
import com.example.financetracker.repository.AppUserMapper;
import com.example.financetracker.repository.CategoryMapper;
import com.example.financetracker.repository.TransactionMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final CategoryMapper categoryMapper;
    private final AppUserMapper appUserMapper;

    public TransactionService(TransactionMapper transactionMapper,
                              CategoryMapper categoryMapper,
                              AppUserMapper appUserMapper){
        this.appUserMapper = appUserMapper;
        this.categoryMapper = categoryMapper;
        this.transactionMapper = transactionMapper;
    }

    @Transactional
    public Transaction addTransaction(Long userId, Long categoryId, BigDecimal amount, String description){

        // check if user exists.
        AppUser user = appUserMapper.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID: " + userId + " not found!"));

        // search the category
        Category category = categoryMapper.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with ID: " + categoryId + " not found!"));

        // create transaction object
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setDate(LocalDateTime.now());
        transaction.setUser(user);
        transaction.setCategory(category);

        // save into database
        return transactionMapper.save(transaction);
    }

}
