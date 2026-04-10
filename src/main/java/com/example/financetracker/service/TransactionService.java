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
import java.util.List;

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

    // User can see all their transactions
    public List<Transaction> getUserTransactions(Long userId){
        return transactionMapper.findByAppUserId(userId);
    }


    // delete a transaction
    @Transactional
    public void deleteTransaction(Long transactionId, Long userId){
        Transaction transaction = transactionMapper.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found!"));

        if(!transaction.getUser().getId().equals(userId)){
            throw new RuntimeException("No permissions to delete this transaction!");
        }


        transactionMapper.delete(transaction);
    }


    @Transactional
    public void updateTransaction(Long transactionId, Long userId, BigDecimal amount, Long categoryId, String description){
        Transaction transaction = transactionMapper.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found!"));


        if(!transaction.getUser().getId().equals(userId)){
            throw new RuntimeException("No permissions to update this transaction!");
        }

        Category category = categoryMapper.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found!"));

        transaction.setAmount(amount);
        transaction.setCategory(category);
        transaction.setDescription(description);

        transactionMapper.save(transaction);
    }
}
