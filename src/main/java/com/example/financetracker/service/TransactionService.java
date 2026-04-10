package com.example.financetracker.service;

import com.example.financetracker.domain.AppUser;
import com.example.financetracker.domain.Category;
import com.example.financetracker.domain.Transaction;
import com.example.financetracker.repository.AppUserRepository;
import com.example.financetracker.repository.CategoryRepository;
import com.example.financetracker.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final AppUserRepository appUserRepository;

    // constructor injection
    public TransactionService(TransactionRepository transactionRepository,
                              CategoryRepository categoryRepository,
                              AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    // add a transaction
    @Transactional
    public Transaction addTransaction(Long userId, Long categoryId, BigDecimal amount, String description){

        // check if user exists.
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID: " + userId + " not found!"));

        // search the category
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with ID: " + categoryId + " not found!"));

        // create transaction object
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setDate(LocalDateTime.now());
        transaction.setUser(user);
        transaction.setCategory(category);

        // save into database
        return transactionRepository.save(transaction);
    }

    // User can see all their transactions
    public List<Transaction> getUserTransactions(Long userId){
        return transactionRepository.findByAppUserId(userId);
    }


    // delete a transaction
    @Transactional
    public void deleteTransaction(Long transactionId, Long userId){
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found!"));

        if(!transaction.getUser().getId().equals(userId)){
            throw new RuntimeException("No permissions to delete this transaction!");
        }

        transactionRepository.delete(transaction);
    }


    // update a transaction
    @Transactional
    public void updateTransaction(Long transactionId, Long userId, BigDecimal amount, Long categoryId, String description){
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found!"));


        if(!transaction.getUser().getId().equals(userId)){
            throw new RuntimeException("No permissions to update this transaction!");
        }

        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found!"));

        transaction.setAmount(amount);
        transaction.setCategory(category);
        transaction.setDescription(description);

        transactionRepository.save(transaction);
    }
}
